package com.marvin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.model.PiracyNotice;
import com.marvin.util.Client;
import com.marvin.util.NoticeSendComponent;
import com.marvin.util.PiracyNoticeTextResolver;
import com.marvin.util.SmsNoticeSendComponent;

@Configuration
@ConditionalOnProperty(prefix = "piracy.sms",name = "enable",havingValue = "true")
public class SmsComponentConfig {

	@Bean
	public SmsNoticeSendComponent<PiracyNotice> smsNoticeComponent(PiracyNoticeTextResolver<PiracyNotice> resolver,
			Client client)
	{
		NoticeSendComponent<PiracyNotice> component = new SmsNoticeSendComponent<PiracyNotice>(resolver, client);
		return (SmsNoticeSendComponent<PiracyNotice>) component;
	}
}
