package com.marvin.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Component
@ConfigurationProperties(prefix = "piracy.dingding")
@PropertySource(value = "application.properties")
public class DingDingProperty {

	private String access_token;//钉钉的accessToken
	
	private String secret;//密钥
	//TODO:后期加入MarkDown

}
