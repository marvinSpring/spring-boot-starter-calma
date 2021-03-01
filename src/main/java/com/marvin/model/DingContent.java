package com.marvin.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class DingContent {//给钉钉发送的内容消息

	private String content;
}
