package com.marvin.config;

import com.marvin.model.message.SmsMessage;
import com.marvin.context.support.client.SmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "calma.sms",name = "enable",havingValue = "true")
@EnableConfigurationProperties(SmsMessage.class)
@Slf4j
public class SmsClientConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public SmsClient clientForSms(SmsMessage noticeStruct) {
		if (log.isDebugEnabled()) {
			log.info("-----------------》》》》》短信组件客户端注入《《《《《《《-------------------------");
		}
		return new SmsClient(noticeStruct);
	}
}
