package com.marvin.util;

import com.marvin.factory.SupportYamlPropertyFactory;
import com.marvin.model.send.DingContent;
import com.marvin.model.send.DingdingExceptionSendContext;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "calma.dingding")
@PropertySource(value = "classpath:application.yml",factory = SupportYamlPropertyFactory.class)
@Data
public class DingDingProperty {

	public DingDingProperty() {
	}

	public DingDingProperty(String access_token, String secret) {
		this.access_token = access_token;
		this.secret = secret;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	private String access_token;//钉钉的accessToken

	private String secret;//密钥

	protected String phone;//手机号

	protected boolean isAll;//是否是通知所有的手机号

	protected boolean enable;

	public DingdingExceptionSendContext createDingdingNotice(DingContent content) {
		return new DingdingExceptionSendContext(content);
	}

	//TODO:后期加入MarkDown


}
