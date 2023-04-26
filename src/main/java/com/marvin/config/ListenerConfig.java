package com.marvin.config;

import com.marvin.component.NoticeSendComponent;
import com.marvin.listener.CalmaNotifier;
import com.marvin.model.CalmaNotice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "calma.exceptionnotice",name = "enabled",havingValue = "true")
public class ListenerConfig {

	@Bean
	@ConditionalOnMissingBean
	public CalmaNotifier calmaNotifier(NoticeSendComponent<CalmaNotice> component) {
		if (log.isDebugEnabled()) {
			log.info("-----------------》》》》》监听开启《《《《《《《-------------------------");
		}
		return new CalmaNotifier(component);
	}
	
}
