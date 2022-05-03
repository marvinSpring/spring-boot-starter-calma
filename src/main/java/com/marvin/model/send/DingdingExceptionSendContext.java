package com.marvin.model.send;

import com.marvin.factory.SupportYamlPropertyFactory;
import com.marvin.model.send.DingContent;
import com.marvin.model.send.ExceptionSendContext;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "calma.dingding")
@PropertySource(value = "classpath:application.yml",factory = SupportYamlPropertyFactory.class)
@Data
/**
 * @Describe: 钉钉异常结构体
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class DingdingExceptionSendContext extends ExceptionSendContext {//最终给钉钉发送的结构体

	protected String msgtype = "text";//发送的文本类型

	public DingdingExceptionSendContext(DingContent text) {
		this.text = text;
	}

	private DingContent text;//发送的消息内容

}
