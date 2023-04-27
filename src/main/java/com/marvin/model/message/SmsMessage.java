package com.marvin.model.message;

import com.marvin.util.factory.SupportYamlPropertyFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "calma.sms")
@PropertySource(value = "classpath:application.yml",factory = SupportYamlPropertyFactory.class)
/**
 * @Describe: Sms异常结构体
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class SmsMessage extends Message{//最终发送短信的结构体

	private boolean enable;
	
	private String regionId;//阿里云的地区id
	
	private String accessKey;//阿里云账户的accessKey

	private String secret;//阿里云账户accessKey对应的密钥
	
	private String phoneNumbers;//接收短信的手机号
	
	private String signName;//阿里云短信的签名
	
	private String templateCode;//阿里云短信的模板码
	
	private Map<String,Object> param;//发送短信的参数  

}
