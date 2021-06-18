package com.marvin.config.web;

import com.marvin.anno.ConditionOnPiracyExceptionNotice;
import com.marvin.handler.PiracyHandler;
import com.marvin.model.PiracyExceptionNotice;
import com.marvin.web.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
@ConditionalOnWebApplication
@ConditionOnPiracyExceptionNotice
@ConditionalOnProperty(name = "piracy.exceptionnotice.listen-type", havingValue = "web")
public class WebExceptionListenConfig implements WebMvcConfigurer, WebMvcRegistrations {

    @Autowired
    private PiracyHandler piracyHandler;

    @Autowired
    private PiracyExceptionNotice piracyExceptionNotice;

    //添加自定义异常处理解析器
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(0, piracyExceptionHandlerResolver());
    }

    private PiracyExceptionHandlerResolver piracyExceptionHandlerResolver() {
        PiracyExceptionHandlerResolver exceptionHandlerResolver = new PiracyExceptionHandlerResolver(piracyHandler, piracyExceptionNotice, currentRequestHeaderResolver(), currentRequestBodyResolver());
        return exceptionHandlerResolver;
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
        ClearBodyInterceptor clearBodyInterceptor = new ClearBodyInterceptor(currentRequestBodyResolver());
        return clearBodyInterceptor;
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
        requestMappingHandlerAdapter.setRequestBodyAdvice(Arrays.asList(currentRequestBodyResolver()));
        return requestMappingHandlerAdapter;
    }
}
