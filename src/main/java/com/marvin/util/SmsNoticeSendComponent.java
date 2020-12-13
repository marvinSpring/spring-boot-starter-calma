package com.marvin.util;

import com.marvin.model.PiracyNotice;
import com.marvin.model.SmsNotice;
import com.marvin.util.sms.SendSmsClient;

public class SmsNoticeSendComponent<T extends PiracyNotice> implements NoticeSendComponent<PiracyNotice > {//短信发送的组件

	private final PiracyNoticeTextResolver<PiracyNotice> resolver;
	
	private final SendSmsClient client;
	
	public SmsNoticeSendComponent(PiracyNoticeTextResolver<PiracyNotice> resolver,Client client) {
		this.client = (SendSmsClient)client;
		this.resolver = resolver;
	}
	
	@Override
	public void send(PiracyNotice piracyNotice) {//这里应该使用smsModel，然后其中封装手机号accesskey等信息
		String text = resolver.resolve(piracyNotice);//将异常解析成便于阅读的结构
		SmsNotice notice = new SmsNotice();
		notice.setPhoneNumbers("");
		notice.setAccessKey("");
		notice.setSecret("");
		notice.setParam(String.valueOf(text.length()));//TODO:通用短信签名和模板没下来，只能发数字，暂时就是异常的长度
		notice.setTemplateCode("");
		notice.setRegionId("");
		notice.setSignName("");
		client.doSend(notice);
	}

}
