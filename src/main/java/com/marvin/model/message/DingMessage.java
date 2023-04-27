package com.marvin.model.message;

import com.marvin.model.message.ding.DingAt;
import com.marvin.util.factory.SupportYamlPropertyFactory;
import com.marvin.model.message.ding.DingText;
import com.marvin.model.message.ding.DingMarkdown;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@EqualsAndHashCode(callSuper = true)
@Component
@ConfigurationProperties(prefix = "calma.dingding")
@PropertySource(value = "classpath:application.yml",factory = SupportYamlPropertyFactory.class)
@Data
@Slf4j
/**
 * @Describe: 钉钉异常结构体
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class DingMessage extends Message{//最终给钉钉发送的结构体

	protected String msgtype;//发送的文本类型

	public DingMessage() {
	}

	public DingMessage(DingText text, String msgtype) {
		this.text = text;
		this.msgtype = msgtype;
	}

	public DingMessage(DingMarkdown markdown, String msgtype) {
		this.markdown = markdown;
		this.msgtype = msgtype;
	}


	private DingText text;

	private DingMarkdown markdown;

	private DingAt at;

}
