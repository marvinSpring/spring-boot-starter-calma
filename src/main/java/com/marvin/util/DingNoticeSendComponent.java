package com.marvin.util;

import com.marvin.model.DingContent;
import com.marvin.model.DingdingNotice;
import com.marvin.model.Notice;
import com.marvin.model.PiracyNotice;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Describe: 发送钉钉通知的组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class DingNoticeSendComponent<T extends PiracyNotice> implements NoticeSendComponent<PiracyNotice>{

	@Autowired
	private DingdingNotice dingdingNotice;

	@Autowired
	DingContent content;
	
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
		content.setContent(text);
		((DingdingNotice) dingdingNotice).setText(content);
		client.doSend(dingdingNotice);
	}
}
