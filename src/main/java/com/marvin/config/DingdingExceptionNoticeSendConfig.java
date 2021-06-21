package com.marvin.config;

import com.marvin.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.model.CalmaNotice;
import com.marvin.util.CalmaNoticeTextResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionOnCalmaExceptionNotice
//@EnableConfigurationProperties(CalmaExceptionProperties.class)
public class DingdingExceptionNoticeSendConfig { 
	
	@Bean
	@ConditionalOnMissingBean
	public CalmaNoticeTextResolver<CalmaNotice> exceptionNotice() {
		return CalmaNotice::createText;
	}
}
