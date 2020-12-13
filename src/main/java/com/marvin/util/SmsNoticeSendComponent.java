package com.marvin.util;

import com.marvin.model.PiracyNotice;
import com.marvin.model.SmsNotice;
import com.marvin.util.sms.SendSmsClient;

public class SmsNoticeSendComponent<T extends PiracyNotice> implements NoticeSendComponent<PiracyNotice > {//短信发送的组件

	private final PiracyNoticeTextResolver<PiracyNotice> resolver;
	
	private final SendSmsClient client;
	
	public SmsNoticeSendComponent(PiracyNoticeTextResolver<PiracyNotice> resolver,SendSmsClient client) {
		this.client = client;
		this.resolver = resolver;
	}
	
	@Override
	public void send(PiracyNotice piracyNotice) {
		String text = resolver.resolve(piracyNotice);//将异常解析成便于阅读的结构
		SmsNotice notice = new SmsNotice();
		notice.setPhoneNumbers("19991962259");
		notice.setAccessKey("LTAI4GAavZLMhfr6hhD6ZPPq");
		notice.setSecret("rFpTMVPy5FGuBsinA95XbaMleWUahQ");
		notice.setParam(String.valueOf(text.length()));//TODO:通用短信签名和模板没下来，只能发数字，暂时就是异常的长度
		notice.setTemplateCode("SMS_204985914");
		notice.setRegionId("cn-hangzhou");
		notice.setSignName("ABC商城");
		client.send(notice);
	}

}
