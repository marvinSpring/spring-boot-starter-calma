package com.marvin.config.handler;

import com.marvin.handler.CalmaHandler;
import com.marvin.model.loader.CalmaExceptionLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CalmaExceptionLoader.class)
@ConditionalOnProperty(prefix = "calma", value = "exceptionnotice.enbaled", matchIfMissing = true)
public class HandlerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CalmaHandler calmaHandler(ApplicationEventPublisher publisher,
                                     CalmaExceptionLoader calmaExceptionNotice) {
        CalmaHandler calmaHandler = new CalmaHandler(publisher, calmaExceptionNotice);
        return calmaHandler;
    }
}
