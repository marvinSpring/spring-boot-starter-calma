package com.marvin.config;

import com.marvin.context.support.client.Client;
import com.marvin.context.support.component.DingNoticeSendComponent;
import com.marvin.context.support.component.NoticeSendComponent;
import com.marvin.model.notice.CommonNotice;
import com.marvin.resolver.CalmaValueResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "calma.exceptionnotice",name = "enabled",havingValue = "true")
public class DingComponentConfiguration {

	@Bean// 注入通知的组件
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "calma.dingding",name = "enable",havingValue = "true")
	public NoticeSendComponent<CommonNotice> registerSendComponent(CalmaValueResolver<CommonNotice> resolver,
                                                                   Client client) {
		if (log.isDebugEnabled()){
			log.info("-----------------》》》》》钉钉通知开启《《《《《《《-------------------------");
		}
		return new DingNoticeSendComponent<>(resolver, client);
	}

}
