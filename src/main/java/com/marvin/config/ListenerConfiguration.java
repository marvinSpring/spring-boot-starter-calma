package com.marvin.config;

import com.marvin.context.support.component.NoticeSendComponent;
import com.marvin.event.listener.CalmaNotifier;
import com.marvin.model.notice.CommonNotice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "calma.exceptionnotice",name = "enabled",havingValue = "true")
public class ListenerConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public CalmaNotifier calmaNotifier(NoticeSendComponent<CommonNotice> component) {
		if (log.isDebugEnabled()) {
			log.info("-----------------》》》》》监听开启《《《《《《《-------------------------");
		}
		return new CalmaNotifier(component);
	}
	
}
