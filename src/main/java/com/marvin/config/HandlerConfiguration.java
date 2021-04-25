package com.marvin.config;

import com.marvin.handler.PiracyHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public PiracyHandler piracyHandler(ApplicationEventPublisher publisher){
        PiracyHandler piracyHandler = new PiracyHandler(publisher);
        return piracyHandler;
    }
}
