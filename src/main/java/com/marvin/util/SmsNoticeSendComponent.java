package com.marvin.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.marvin.model.PiracyNotice;
import com.marvin.model.SmsNotice;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Describe: Sms的发送组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class SmsNoticeSendComponent<T extends PiracyNotice> implements NoticeSendComponent<PiracyNotice> {// 短信发送的组件

	private final PiracyNoticeTextResolver<PiracyNotice> resolver;

	private final SendSmsClient client;

	@Autowired
	private SmsNotice smsNotice;

	public SmsNoticeSendComponent(PiracyNoticeTextResolver<PiracyNotice> resolver, Client client) {
		this.client = (SendSmsClient) client;
		this.resolver = resolver;
	}

	@Override
	public void send(PiracyNotice piracyNotice) {
		Map<String, Object> map = this.smsResolve(piracyNotice);// 将异常解析成短信模板结构
		smsNotice.setParam(map);
		client.doSend(smsNotice);
	}

	private Map<String,Object> smsResolve(PiracyNotice piracyNotice) {
		Map<String, Object> map = null;
		try {
			map = new HashMap<>();
			map.put("project",piracyNotice.getProjectName());
			LocalDateTime time = piracyNotice.getCreateTime();
			map.put("time",time.getYear()+"-"+time.getMonthValue()+"-"+time.getDayOfMonth()+" "+time.getHour()+":"+time.getMinute()+":"+time.getSecond());
			for (String x : piracyNotice.getExceptionMessage()) {
				map.put("causeBy",x.substring(x.indexOf(":")+1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
