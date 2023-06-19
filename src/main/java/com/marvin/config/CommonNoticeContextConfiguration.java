package com.marvin.config;

import com.marvin.context.DefaultNoticeContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnProperty(prefix = "calma", value = "exceptionnotice.enbaled", matchIfMissing = true)
public class CommonNoticeContextConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @Resource
    public DefaultNoticeContext noticeContext(ApplicationEventPublisher publisher) {
        return new DefaultNoticeContext(publisher);
    }
}
