package com.marvin.component;

import com.marvin.client.Client;
import com.marvin.client.SendSmsClient;
import com.marvin.model.CalmaNotice;
import com.marvin.model.SmsNotice;
import com.marvin.util.CalmaValueResolver;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Describe: Sms的发送组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class SmsNoticeSendComponent<T extends CalmaNotice> implements NoticeSendComponent<CalmaNotice> {// 短信发送的组件

	private final CalmaValueResolver<CalmaNotice> resolver;

	private final SendSmsClient client;

	@Autowired
	private SmsNotice smsNotice;

	public SmsNoticeSendComponent(CalmaValueResolver<CalmaNotice> resolver, Client client) {
		this.client = (SendSmsClient) client;
		this.resolver = resolver;
	}

	@Override
	public void send(CalmaNotice calmaNotice) {
		Map<String, Object> map = this.smsResolve(calmaNotice);// 将异常解析成短信模板结构
		smsNotice.setParam(map);
		client.doSend(smsNotice);
	}

	private Map<String,Object> smsResolve(CalmaNotice calmaNotice) {
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
