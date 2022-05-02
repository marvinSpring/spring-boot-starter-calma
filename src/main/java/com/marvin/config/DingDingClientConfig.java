package com.marvin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.client.Client;
import com.marvin.client.DefaultDingdingClient;
import com.marvin.util.DingDingProperty;

@Configuration
@ConditionalOnProperty(prefix = "calma.dingding",name = "enable",havingValue = "true")
@EnableConfigurationProperties(DingDingProperty.class)
@Slf4j
public class DingDingClientConfig {//注入发送钉钉信息的客户端

	@Bean
	@ConditionalOnMissingBean
	public Client dingDingClient(DingDingProperty dingProperty) {
		Client client = new DefaultDingdingClient(dingProperty);
		return client;
	}
}
