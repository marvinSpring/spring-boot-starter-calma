package com.marvin.config;

import com.marvin.model.CalmaNotice;
import com.marvin.util.CalmaNoticeTextResolver;
import com.marvin.util.Client;
import com.marvin.util.NoticeSendComponent;
import com.marvin.util.SmsNoticeSendComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "calma.sms", name = "enable", havingValue = "true")
@Slf4j
public class SmsComponentConfig {

    @Bean
    @ConditionalOnMissingBean
    public SmsNoticeSendComponent<CalmaNotice> smsNoticeComponent(CalmaNoticeTextResolver<CalmaNotice> resolver,
                                                                  Client client) {
        log.info("-----------------》》》》》短信通知开启《《《《《《《-------------------------");
        NoticeSendComponent<CalmaNotice> component = new SmsNoticeSendComponent<CalmaNotice>(resolver, client);
        return (SmsNoticeSendComponent<CalmaNotice>) component;
    }
}
