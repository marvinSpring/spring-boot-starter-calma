package com.marvin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.model.SmsNotice;
import com.marvin.util.Client;
import com.marvin.util.DefaultDingdingClient;
import com.marvin.util.sms.SendSmsClient;

@Configuration
@ConditionalOnProperty(prefix = "piracy.sms",name = "enable",havingValue = "true")
public class SmsClientConfig {

	@Bean
	@ConditionalOnMissingBean
	public SendSmsClient clientForSms(SmsNotice noticeStruct) {
		Client client = new SendSmsClient(noticeStruct);
		return (SendSmsClient) client;
	}
}
