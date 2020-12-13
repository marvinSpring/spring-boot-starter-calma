package com.marvin.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "piracy.dingding")
public class DingdingNoticeProperty {
	
	private String phone;
	
	private String accessToken;//钉钉的access_token
	
	private String signSecrect;//密钥
	
	
	
	

}
