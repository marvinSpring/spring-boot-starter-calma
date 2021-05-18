package com.marvin.model;

import com.marvin.util.SupportYamlPropertyFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "piracy.dingding")
@PropertySource(value = "classpath:application.yml",factory = SupportYamlPropertyFactory.class)
@Data
/**
 * @Describe: 钉钉异常结构体
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class DingdingNotice extends Notice{//最终给钉钉发送的结构体

	protected String msgtype = "text";//发送的文本类型

	protected String phone;//手机号

	protected boolean isAll;//是否是通知所有的手机号
	
	private DingContent text;//发送的消息内容

	protected boolean enable;

}
