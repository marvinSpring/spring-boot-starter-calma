package com.marvin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.model.PiracyNotice;
import com.marvin.util.Client;
import com.marvin.util.DingNoticeSendComponent;
import com.marvin.util.NoticeSendComponent;
import com.marvin.util.PiracyNoticeTextResolver;
import com.marvin.util.SmsNoticeSendComponent;

@Configuration // 注入通知的组件
public class RegisterConfig {

	@Bean
	public NoticeSendComponent<PiracyNotice> registerSendComponent(PiracyNoticeTextResolver<PiracyNotice> resolver,
			Client client) {
//		NoticeSendComponent<PiracyNotice> component = new DingNoticeSendComponent<PiracyNotice>(resolver, client);
		NoticeSendComponent<PiracyNotice> component = new SmsNoticeSendComponent<PiracyNotice>(resolver, client);
		return component;
	}

}
