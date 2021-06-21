package com.marvin.config;

import com.marvin.listener.AbstractCalmaNotifier;
import com.marvin.listener.CalmaNotifier;
import com.marvin.model.CalmaNotice;
import com.marvin.util.NoticeSendComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ListenerConfig {

	@Bean
	@ConditionalOnMissingBean
	public CalmaNotifier piracyNotifier(NoticeSendComponent<CalmaNotice> component) {
		log.info("-----------------》》》》》监听开启《《《《《《《-------------------------");
		AbstractCalmaNotifier piracyNotifier = new CalmaNotifier(component);
		return (CalmaNotifier) piracyNotifier;
	}
	
}
