package com.marvin.config;

import com.marvin.context.DefaultNoticeContext;
import com.marvin.context.AbstractNoticeContext;
import com.marvin.model.web.NoticeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnProperty(prefix = "calma", value = "exceptionnotice.enbaled", matchIfMissing = true)
public class HandlerConfiguration {

    @Autowired(required = false)
    private NoticeInfo noticeInfo;

    @Bean
    @ConditionalOnMissingBean
    @Resource
    public AbstractNoticeContext noticeContext(ApplicationEventPublisher publisher) {
        return new DefaultNoticeContext(publisher,noticeInfo);
    }
}
