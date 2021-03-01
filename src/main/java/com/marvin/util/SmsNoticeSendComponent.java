package com.marvin.util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.marvin.model.PiracyNotice;
import com.marvin.model.SmsNotice;
import com.marvin.util.sms.SendSmsClient;

/**
 * @Describe: Sms的发送组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class SmsNoticeSendComponent<T extends PiracyNotice> implements NoticeSendComponent<PiracyNotice> {// 短信发送的组件

	private final PiracyNoticeTextResolver<PiracyNotice> resolver;

	private final SendSmsClient client;

	public SmsNoticeSendComponent(PiracyNoticeTextResolver<PiracyNotice> resolver, Client client) {
		this.client = (SendSmsClient) client;
		this.resolver = resolver;
	}

	@Override
	public void send(PiracyNotice piracyNotice) {// 这里应该使用smsModel，然后其中封装手机号accesskey等信息
		Map<String, Object> map = this.smsResolve(piracyNotice);// 将异常解析成短信模板结构
		SmsNotice smsNotice = new SmsNotice();
		smsNotice.setParam(map);
		client.doSend(smsNotice);
	}

	private Map<String,Object> smsResolve(PiracyNotice piracyNotice) {
		Map<String, Object> map = new HashMap<>();
		map.put("project",piracyNotice.getProjectName());
		map.put("time",piracyNotice.getCreateTime());
		for (String x : piracyNotice.getExceptionMessage()) {
			map.put("causeBy",x.substring(x.indexOf(":")+1));
		}
		return map;
	}

}
