package com.marvin.util;

import com.marvin.model.DingText;
import com.marvin.model.DingdingNotice;
import com.marvin.model.PiracyNotice;

//发送钉钉通知的组件 
public class DingNoticeSendComponent<T extends PiracyNotice> implements NoticeSendComponent<PiracyNotice>{
	
	private final PiracyNoticeTextResolver<PiracyNotice> resolver;
	
	private final DingdingClient client;

	public DingNoticeSendComponent(PiracyNoticeTextResolver<PiracyNotice> resolver,DingdingClient client) {
		this.client = client;
		this.resolver = resolver;
	}

	//将异常结构体组装好 
	@Override
	public void send(PiracyNotice exceptionNotice) {
		String text = resolver.resolve(exceptionNotice);
		DingdingNotice dingdingNotice = new DingdingNotice();
		dingdingNotice.setPhone("19991962259");//由于图方便就不从配置文件中读取手机号了
		DingText content = new DingText();
		content.setContent(text);
		dingdingNotice.setText(content);
		client.doSend(dingdingNotice);
	}

	public DingdingClient getClient() {
		return client;
	}
}
