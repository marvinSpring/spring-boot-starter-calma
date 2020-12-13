package com.marvin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.util.Client;
import com.marvin.util.DefaultDingdingClient;
import com.marvin.util.sms.SendSmsClient;

@Configuration
public class SmsClientConfig {

	@Bean
	@ConditionalOnMissingBean
	public SendSmsClient clientForSms() {
		Client client = new SendSmsClient();
		return (SendSmsClient) client;
	}
}
