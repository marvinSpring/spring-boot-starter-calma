package com.marvin.config;

import com.marvin.anno.PiracyExceptionListener;
import com.marvin.handler.PiracyHandler;
import com.marvin.model.PiracyExceptionNotice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PiracyExceptionNotice.class)
@ConditionalOnProperty(prefix = "piracy", value = "exceptionnotice.enbaled", matchIfMissing = true)
public class HandlerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public PiracyHandler piracyHandler(ApplicationEventPublisher publisher,
                                       PiracyExceptionNotice piracyExceptionNotice) {
        PiracyHandler piracyHandler = new PiracyHandler(publisher, piracyExceptionNotice);
        return piracyHandler;
    }
}
