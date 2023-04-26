package com.marvin.config;

import com.marvin.handler.CalmaHandler;
import com.marvin.model.CalmaExceptionNotice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CalmaExceptionNotice.class)
@ConditionalOnProperty(prefix = "calma", value = "exceptionnotice.enbaled", matchIfMissing = true)
public class HandlerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CalmaHandler calmaHandler(ApplicationEventPublisher publisher,
                                      CalmaExceptionNotice calmaExceptionNotice) {

        return new CalmaHandler(publisher, calmaExceptionNotice);
    }
}
