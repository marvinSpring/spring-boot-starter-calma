package com.marvin.config;

import com.marvin.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.model.PiracyNotice;

@Configuration 
@ConditionalOnProperty(prefix = "piracy.dingding",name = "enable",havingValue = "true")
@Slf4j
public class DingComponentConfig {
	public DingComponentConfig() {
		log.info("-----------------》》》》》11111《《《《《《《-------------------------");
	}

	@Bean// 注入通知的组件
	@ConditionalOnMissingBean
	public NoticeSendComponent<PiracyNotice> registerSendComponent(PiracyNoticeTextResolver<PiracyNotice> resolver,
			Client client) {
		log.info("-----------------》》》》》钉钉通知开启《《《《《《《-------------------------");
		NoticeSendComponent<PiracyNotice> component = new DingNoticeSendComponent<PiracyNotice>(resolver, client);
		return component;
	}

}
