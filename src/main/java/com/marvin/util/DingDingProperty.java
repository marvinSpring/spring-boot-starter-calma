package com.marvin.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DingDingProperty {

	private String [] phoneNum;//手机号
	
	private String accessToken;//钉钉的accessToken
	
	private String signSecret;//密钥
	//TODO:后期加入MarkDown

	public DingDingProperty(String[] phoneNum, String accessToken, String signSecret) {
		this.phoneNum = phoneNum;
		this.accessToken = accessToken;
		this.signSecret = signSecret;
	}
	
}
