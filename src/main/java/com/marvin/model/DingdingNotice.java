package com.marvin.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "piracy.dingding")
@PropertySource(value = "application.properties")
public class DingdingNotice extends Notice{//最终给钉钉发送的结构体

//	@Value("${piracy.msgtype}")
	private String msgtype = "text";//发送的文本类型

//	@Value("${piracy.phone}")
	protected String phone;//手机号

//	@Value("${piracy.all}")
	protected boolean isAll;//是否是通知所有的手机号
	
	private DingText text;//发送的消息内容

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAll() {
		return isAll;
	}

	public void setAll(boolean isAll) {
		this.isAll = isAll;
	}

	public DingText getText() {
		return text;
	}

	public void setText(DingText text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "DingdingNotice [msgtype=" + msgtype + ", phone=" + phone + ", isAll=" + isAll + ", text=" + text + "]";
	}

}
