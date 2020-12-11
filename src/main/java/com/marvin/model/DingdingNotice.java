package com.marvin.model;

public class DingdingNotice {

	private String msgtype = "text";
	
	protected String phone;

	protected boolean isAll;
	
	private DingText text;

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
