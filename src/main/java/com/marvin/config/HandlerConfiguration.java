package com.marvin.config;

import com.marvin.handler.DispatcherHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "calma", value = "exceptionnotice.enbaled", matchIfMissing = true)
public class HandlerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DispatcherHandler calmaHandler(ApplicationEventPublisher publisher) {
        return new DispatcherHandler(publisher);
    }
}
