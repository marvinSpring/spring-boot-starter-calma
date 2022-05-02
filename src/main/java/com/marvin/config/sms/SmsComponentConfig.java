package com.marvin.config.sms;

import com.marvin.model.loader.SmartExceptionLoader;
import com.marvin.util.CalmaNoticeTextResolver;
import com.marvin.util.client.Client;
import com.marvin.util.component.SmsNoticeSendComponent;
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
    public SmsNoticeSendComponent<SmartExceptionLoader> smsNoticeComponent(CalmaNoticeTextResolver<SmartExceptionLoader> resolver,
                                                                           Client client) {
        log.info("-----------------》》》》》短信通知开启《《《《《《《-------------------------");
        return new SmsNoticeSendComponent<>(resolver, client);
    }
}
