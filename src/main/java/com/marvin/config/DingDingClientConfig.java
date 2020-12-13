package com.marvin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.marvin.model.DingdingNotice;
import com.marvin.util.DefaultDingdingClient;
import com.marvin.util.DingDingProperty;
import com.marvin.util.DingdingClient;

@Configuration
public class DingDingClientConfig {//注入发送钉钉信息的客户端

	@Bean(name = "client")
	public DingdingClient dingDingClient() { 
		DefaultDingdingClient client = new DefaultDingdingClient();
		return client;
	}
}
