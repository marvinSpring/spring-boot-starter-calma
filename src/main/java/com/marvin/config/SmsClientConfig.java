package com.marvin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.model.SmsNotice;
import com.marvin.util.Client;
import com.marvin.util.DefaultDingdingClient;
import com.marvin.util.sms.SendSmsClient;

@Configuration
@ConditionalOnProperty(prefix = "piracy.sms",name = "enable",havingValue = "true")
@EnableConfigurationProperties(SmsNotice.class)
@Slf4j
public class SmsClientConfig {

	@Bean
	@ConditionalOnMissingBean
	public SendSmsClient clientForSms(@Qualifier("smsNotice") SmsNotice noticeStruct) {
		Client client = new SendSmsClient(noticeStruct);
		return (SendSmsClient) client;
	}
}
