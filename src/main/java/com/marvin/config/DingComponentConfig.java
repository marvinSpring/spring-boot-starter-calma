package com.marvin.config;

import com.marvin.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.model.CalmaNotice;
import com.marvin.util.CalmaNoticeTextResolver;
import com.marvin.util.Client;
import com.marvin.util.DingNoticeSendComponent;
import com.marvin.util.NoticeSendComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "calma.dingding",name = "enable",havingValue = "true")
@Slf4j
public class DingComponentConfig {

	@Bean// 注入通知的组件
	@ConditionalOnMissingBean
	@ConditionOnCalmaExceptionNotice
	public NoticeSendComponent<CalmaNotice> registerSendComponent(CalmaNoticeTextResolver<CalmaNotice> resolver,
																  Client client) {
		log.info("-----------------》》》》》钉钉通知开启《《《《《《《-------------------------");
		NoticeSendComponent<CalmaNotice> component = new DingNoticeSendComponent<CalmaNotice>(resolver, client);
		return component;
	}

}
