package com.marvin.util.component;

import com.marvin.model.loader.SmartExceptionLoader;
import com.marvin.model.send.SmsExceptionSendContext;
import com.marvin.util.CalmaNoticeTextResolver;
import com.marvin.util.client.Client;
import com.marvin.util.client.SendSmsClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Describe: Sms的发送组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class SmsNoticeSendComponent<T extends SmartExceptionLoader> implements NoticeSendComponent<SmartExceptionLoader> {// 短信发送的组件

	private final CalmaNoticeTextResolver<SmartExceptionLoader> resolver;

	private final SendSmsClient client;

	@Autowired
	private SmsExceptionSendContext smsExceptionContext;

	public SmsNoticeSendComponent(CalmaNoticeTextResolver<SmartExceptionLoader> resolver, Client client) {
		this.client = (SendSmsClient) client;
		this.resolver = resolver;
	}

	@Override
	public void send(SmartExceptionLoader calmaNotice) {
		Map<String, Object> map = this.smsResolve(calmaNotice);// 将异常解析成短信模板结构
		smsExceptionContext.setParam(map);
		client.doSend(smsExceptionContext);
	}

	private Map<String,Object> smsResolve(SmartExceptionLoader calmaNotice) {
		Map<String, Object> map = null;
		try {
			map = new HashMap<>();
			map.put("project",calmaNotice.getProjectName());
			LocalDateTime time = calmaNotice.getCreateTime();
			map.put("time",time.getYear()+"-"+time.getMonthValue()+"-"+time.getDayOfMonth()+" "+time.getHour()+":"+time.getMinute()+":"+time.getSecond());
			for (String x : calmaNotice.getExceptionMessage()) {
				map.put("causeBy",x.substring(x.indexOf(":")+1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
