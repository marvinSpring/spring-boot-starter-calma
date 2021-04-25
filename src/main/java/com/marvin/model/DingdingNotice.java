package com.marvin.model;

import com.marvin.util.SupportYamlPropertyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "piracy.dingding")
@PropertySource(value = "classpath:application.yml",factory = SupportYamlPropertyFactory.class)
@Data
@Slf4j
/**
 * @Describe: 钉钉异常结构体
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class DingdingNotice extends Notice{//最终给钉钉发送的结构体

	protected String msgtype = "text";//发送的文本类型

	public DingdingNotice(DingContent text) {
		this.text = text;
	}

	private DingContent text;//发送的消息内容

}
