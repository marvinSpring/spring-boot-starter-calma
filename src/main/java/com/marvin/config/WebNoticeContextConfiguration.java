package com.marvin.config;

import com.marvin.context.HttpNoticeContext;
import com.marvin.model.web.WebNoticeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnProperty(prefix = "calma", value = "exceptionnotice.listen-type", havingValue = "WEB")
public class WebNoticeContextConfiguration {

    @Autowired(required = false)
    private WebNoticeInfo noticeInfo;

    @Bean
    @ConditionalOnMissingBean
    @Resource
    public HttpNoticeContext noticeContext(ApplicationEventPublisher publisher) {
        return new HttpNoticeContext(publisher,noticeInfo);
    }

}
