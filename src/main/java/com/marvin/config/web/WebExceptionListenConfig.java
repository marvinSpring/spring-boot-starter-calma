package com.marvin.config.web;

import com.marvin.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.handler.CalmaHandler;
import com.marvin.model.loader.CalmaExceptionLoader;
import com.marvin.web.*;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Collections;
import java.util.List;

@Configuration
@ConditionalOnWebApplication
@ConditionOnCalmaExceptionNotice
@ConditionalOnProperty(name = "calma.exceptionnotice.listen-type", havingValue = "WEB")
public class WebExceptionListenConfig implements WebMvcConfigurer, WebMvcRegistrations  {

    protected final org.apache.commons.logging.Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private CalmaHandler calmaHandler;

    @Autowired
    private CalmaExceptionLoader calmaExceptionLoader;

    //添加自定义异常处理解析器
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(0,calmaExceptionHandlerResolver());
    }

    private CalmaExceptionHandlerResolver calmaExceptionHandlerResolver() {
        log.info("----------------------进入web模式----------------------");
        return new CalmaExceptionHandlerResolver(calmaHandler, calmaExceptionLoader, currentRequestHeaderResolver(), currentRequestBodyResolver());
    }

    //------------
    //注入请求头和请求体组件
    //-----------
    @Bean
    public CurrentRequestBodyResolver currentRequestBodyResolver() {
        return new DefaultRequestBodyResolver();
    }

    @Bean
    @ConditionalOnMissingBean(value = CurrentRequestHeaderResolver.class)
    public CurrentRequestHeaderResolver currentRequestHeaderResolver() {
        return new DefaultRequestHeaderResolver();
    }

    @Bean
    public ClearBodyInterceptor clearBodyInterceptor() {
        return new ClearBodyInterceptor(currentRequestBodyResolver());
    }

    //添加自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clearBodyInterceptor());
    }

    //给处理器适配器设置自定义的请求体解析器
    @Override
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        requestMappingHandlerAdapter.setRequestBodyAdvice(Collections.singletonList(currentRequestBodyResolver()));
        return requestMappingHandlerAdapter;
    }
}
