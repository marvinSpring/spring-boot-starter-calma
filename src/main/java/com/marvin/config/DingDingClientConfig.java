package com.marvin.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.util.Client;
import com.marvin.util.DefaultDingdingClient;
import com.marvin.util.DingDingProperty;

@Configuration
@ConditionalOnProperty(prefix = "piracy.dingding",name = "enable",havingValue = "true")
@EnableConfigurationProperties(DingDingProperty.class)
public class DingDingClientConfig {//注入发送钉钉信息的客户端

	@Bean
	@ConditionalOnMissingBean
	public Client dingDingClient(@Qualifier("dingDingProperty") DingDingProperty dingProperty) {
		Client client = new DefaultDingdingClient(dingProperty);
		return client;
	}
}
