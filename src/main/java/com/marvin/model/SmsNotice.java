package com.marvin.model;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "piracy.sms")
@PropertySource(value = "application.properties")
public class SmsNotice extends Notice{//最终发送短信的结构体
	
	private String regionId;//阿里云的地区id
	
	private String accessKey;//阿里云账户的accessKey

	private String secret;//阿里云账户accessKey对应的密钥
	
	private String phoneNumbers;//接收短信的手机号
	
	private String signName;//阿里云短信的签名
	
	private String templateCode;//阿里云短信的模板码
	
	private Map<String,Object> param;//发送短信的参数  

}
