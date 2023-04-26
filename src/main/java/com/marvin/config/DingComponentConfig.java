package com.marvin.config;

import com.marvin.client.Client;
import com.marvin.component.DingNoticeSendComponent;
import com.marvin.component.NoticeSendComponent;
import com.marvin.model.CalmaNotice;
import com.marvin.util.CalmaValueResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "calma.exceptionnotice",name = "enabled",havingValue = "true")
public class DingComponentConfig {

	@Bean// 注入通知的组件
	@ConditionalOnMissingBean
//	@ConditionOnCalmaExceptionNotice
	@ConditionalOnProperty(prefix = "calma.dingding",name = "enable",havingValue = "true")
	public NoticeSendComponent<CalmaNotice> registerSendComponent(CalmaValueResolver<CalmaNotice> resolver,
																  Client client) {
		if (log.isDebugEnabled()){
			log.info("-----------------》》》》》钉钉通知开启《《《《《《《-------------------------");
		}
		return new DingNoticeSendComponent<>(resolver, client);
	}

}
