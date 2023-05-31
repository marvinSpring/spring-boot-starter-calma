package com.marvin.context.support.component;

import com.marvin.context.support.client.Client;
import com.marvin.context.support.client.SmsClient;
import com.marvin.model.notice.CommonNotice;
import com.marvin.model.message.SmsMessage;
import com.marvin.resolver.CalmaValueResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Describe: Sms的发送组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@Slf4j
public class SmsNoticeSendComponent<T extends CommonNotice> implements NoticeSendComponent<CommonNotice> {// 短信发送的组件

	private final CalmaValueResolver<CommonNotice> resolver;

	private final SmsClient smsClient;

	@Autowired
	private SmsMessage smsNotice;

	public SmsNoticeSendComponent(CalmaValueResolver<CommonNotice> resolver, Client client) {
		this.smsClient = (SmsClient) client;
		this.resolver = resolver;
	}

	@Override
	public void send(CommonNotice calmaNotice) {
		if (log.isDebugEnabled()){
			log.info("--------------->>>>>sms<<<<<<<<-----------------------");
		}
		Map<String, Object> map = this.toSmsMap(calmaNotice);// 将异常转换为短信模板结构
		smsNotice.setParam(map);
		smsClient.doSend(smsNotice);
	}

	private Map<String,Object> toSmsMap(CommonNotice calmaNotice) {
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
