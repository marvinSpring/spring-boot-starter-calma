package com.marvin.config;

import com.marvin.anno.ConditionOnPiracyExceptionNotice;
import com.marvin.aop.PiracyAop;
import com.marvin.handler.PiracyHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionOnPiracyExceptionNotice
public class AopConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public PiracyAop piracyAop(PiracyHandler piracyHandler ){
        return new PiracyAop(piracyHandler);
    }

}
