package com.marvin.model;

public class SmsNotice {//最终发送短信的结构体
	
	private String regionId;//阿里云的地区id
	
	private String accessKey;//阿里云账户的accessKey

	private String secret;//阿里云账户accessKey对应的密钥
	
	private String phoneNumbers;//接收短信的手机号
	
	private String signName;//阿里云短信的签名
	
	private String templateCode;//阿里云短信的模板码
	
	private String param;//发送短信的参数

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
}
