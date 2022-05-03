package com.marvin.config.sms;

import com.marvin.model.send.SmsExceptionSendContext;
import com.marvin.util.client.Client;
import com.marvin.util.client.SendSmsClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "calma.sms",name = "enable",havingValue = "true")
@EnableConfigurationProperties(SmsExceptionSendContext.class)
public class SmsClientConfig {

	@Bean
	@ConditionalOnMissingBean
	public SendSmsClient clientForSms(SmsExceptionSendContext noticeStruct) {
		Client client = new SendSmsClient(noticeStruct);
		return (SendSmsClient) client;
	}
}
