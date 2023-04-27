package com.marvin.config.web;

import com.marvin.config.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.handler.DispatcherHandler;
import com.marvin.web.interceptor.ClearBodyInterceptor;
import com.marvin.web.resolver.*;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class WebExceptionListenConfig implements WebMvcConfigurer, WebMvcRegistrations  {

    @Autowired
    private DispatcherHandler calmaHandler;

    //添加自定义异常处理解析器
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        if (log.isDebugEnabled()) {
            log.info("----------------------进入web模式----------------------");
        }
        resolvers.add(0,calmaExceptionHandlerResolver());
    }

    private CalmaExceptionHandlerResolver calmaExceptionHandlerResolver() {
        return new CalmaExceptionHandlerResolver(calmaHandler, currentRequestHeaderResolver(), currentRequestBodyResolver());
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
