package com.marvin.util;

import com.marvin.model.DingContent;
import com.marvin.model.DingdingNotice;
import com.marvin.model.Notice;
import com.marvin.model.PiracyNotice;

//发送钉钉通知的组件 
public class DingNoticeSendComponent<T extends PiracyNotice> implements NoticeSendComponent<PiracyNotice>{
	
	private final PiracyNoticeTextResolver<PiracyNotice> resolver;
	
	private final Client client;

	public DingNoticeSendComponent(PiracyNoticeTextResolver<PiracyNotice> resolver,Client client) {
		this.client = client;
		this.resolver = resolver;
	}

	//将异常结构体组装好 
	@Override
	public void send(PiracyNotice exceptionNotice) {
		String text = resolver.resolve(exceptionNotice);
		Notice dingdingNotice = new DingdingNotice();
		DingContent content = new DingContent();//为了满足钉钉接口的参数格式而创建的类对象
		content.setContent(text);
		((DingdingNotice) dingdingNotice).setContent(content);
		client.doSend(dingdingNotice);
	}
}
