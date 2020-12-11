package com.marvin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.marvin.model.DingdingNotice;
import com.marvin.util.DefaultDingdingClient;
import com.marvin.util.DingDingProperty;
import com.marvin.util.DingdingClient;

@Configuration
public class DingDingClientConfig {

	@Bean
	public DingdingClient dingDingClient() {//DingDingProperty property,Gson gson
		return new DefaultDingdingClient();
	}
}
