package com.marvin.config.sms;

import com.marvin.model.SmsNotice;
import com.marvin.util.Client;
import com.marvin.util.SendSmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "calma.sms",name = "enable",havingValue = "true")
@EnableConfigurationProperties(SmsNotice.class)
@Slf4j
public class SmsClientConfig {

	@Bean
	@ConditionalOnMissingBean
	public SendSmsClient clientForSms(SmsNotice noticeStruct) {
		Client client = new SendSmsClient(noticeStruct);
		return (SendSmsClient) client;
	}
}
