package com.marvin.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "piracy")
@PropertySource(value = "application.properties")
public class DingDingProperty {//钉钉消息需要的参数——梦的面包

//	@Value("${piracy.access_token}")//ac39ef481aff92d19dc21ec6df05e5f2b2a6870e6046f3f95beb5b34110e2a3e
	private String access_token;//钉钉的accessToken
	
//	@Value("${piracy.secret}")//SECb202b52aedf7d14fb7f2d40bf76625aef65a64fb1e54d1c82f9aa84cbb6a8077
	private String secret;//密钥
	//TODO:后期加入MarkDown

//	public DingDingProperty(String accessToken, String signSecret) {
//		this.accessToken = accessToken;
//		this.signSecret = signSecret;
//	}
//	
}
