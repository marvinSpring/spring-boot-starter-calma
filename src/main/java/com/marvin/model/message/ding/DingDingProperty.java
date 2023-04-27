package com.marvin.model.message.ding;

import com.marvin.util.factory.SupportYamlPropertyFactory;
import com.marvin.model.message.DingMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

//@Component
@ConfigurationProperties(prefix = "calma.dingding")
@PropertySource(value = "classpath:application.yml",factory = SupportYamlPropertyFactory.class)
@Data
@Slf4j
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

	protected String phoneNumbers;//手机号

	protected String userIds;//手机号

	protected String isAtAll;//是否是通知所有的手机号

	protected boolean enable;

	protected String msgtype;

	public boolean isAtAll(){
		return !StringUtils.isEmpty(isAtAll) && (isAtAll.equalsIgnoreCase("true"));
	}

	public DingMessage createDingdingTextNotice(DingText content) {
		return new DingMessage(content,msgtype);
	}

	//加入MarkDown
	public DingMessage createDingdingMarkdownNotice(DingMarkdown markdown) {
		return new DingMessage(markdown,msgtype);
	}

}
