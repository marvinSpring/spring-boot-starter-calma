package com.marvin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.model.PiracyNotice;
import com.marvin.util.Client;
import com.marvin.util.DingNoticeSendComponent;
import com.marvin.util.NoticeSendComponent;
import com.marvin.util.PiracyNoticeTextResolver;
import com.marvin.util.SmsNoticeSendComponent;

@Configuration 
@ConditionalOnProperty(prefix = "piracy.dingding",name = "enable",havingValue = "true")
public class DingComponentConfig {

	@Bean// 注入通知的组件
	public NoticeSendComponent<PiracyNotice> registerSendComponent(PiracyNoticeTextResolver<PiracyNotice> resolver,
			Client client) {
		NoticeSendComponent<PiracyNotice> component = new DingNoticeSendComponent<PiracyNotice>(resolver, client);
		return component;
	}

}
