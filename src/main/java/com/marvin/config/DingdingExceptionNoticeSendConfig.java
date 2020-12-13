package com.marvin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.anno.ConditionOnPiracyExceptionNotice;
import com.marvin.model.PiracyNotice;
import com.marvin.util.PiracyNoticeTextResolver;

@Configuration
@ConditionOnPiracyExceptionNotice
//@EnableConfigurationProperties(PiracyExceptionProperties.class)
public class DingdingExceptionNoticeSendConfig {//当异常通知这个文件存在则注入异常通知的文本解析器
	
	@Bean
	@ConditionalOnMissingBean
	public PiracyNoticeTextResolver<PiracyNotice> exceptionNotice() {
		return x->x.createText();
	}
}
