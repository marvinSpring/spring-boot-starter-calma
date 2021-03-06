package com.marvin.config.ding;

import com.marvin.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.model.loader.SmartExceptionLoader;
import com.marvin.util.CalmaNoticeTextResolver;
import com.marvin.util.client.Client;
import com.marvin.util.component.DingNoticeSendComponent;
import com.marvin.util.component.NoticeSendComponent;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "calma.dingding",name = "enable",havingValue = "true")
public class DingComponentConfig {

	protected final org.apache.commons.logging.Log log = LogFactory.getLog(this.getClass());

	@Bean// 注入通知的组件
	@ConditionalOnMissingBean
	@ConditionOnCalmaExceptionNotice
	public NoticeSendComponent<SmartExceptionLoader> registerSendComponent(CalmaNoticeTextResolver<SmartExceptionLoader> resolver,
																		   Client client) {
		log.info("----------------->>>>>钉钉通知开启<<<<<-------------------------");
		return new DingNoticeSendComponent<>(resolver, client);
	}

}
