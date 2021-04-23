package com.marvin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.model.PiracyNotice;
import com.marvin.util.Client;
import com.marvin.util.NoticeSendComponent;
import com.marvin.util.PiracyNoticeTextResolver;
import com.marvin.util.SmsNoticeSendComponent;

@Configuration
@ConditionalOnProperty(prefix = "piracy.sms", name = "enable", havingValue = "true")
@Slf4j
public class SmsComponentConfig {

    @Bean
    @ConditionalOnMissingBean
    public SmsNoticeSendComponent<PiracyNotice> smsNoticeComponent(PiracyNoticeTextResolver<PiracyNotice> resolver,
                                                                   Client client) {
        log.info("-----------------》》》》》短信通知开启《《《《《《《-------------------------");
        NoticeSendComponent<PiracyNotice> component = new SmsNoticeSendComponent<PiracyNotice>(resolver, client);
        return (SmsNoticeSendComponent<PiracyNotice>) component;
    }
}
