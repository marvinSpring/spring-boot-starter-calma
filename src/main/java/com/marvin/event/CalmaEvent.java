package com.marvin.event;

import com.marvin.model.CalmaNotice;
import org.springframework.context.ApplicationEvent;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * @Describe: 异常事件，将异常事件包装成异常事件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class CalmaEvent extends ApplicationEvent{//异常事件驱动器
	
	private static final long serialVersionUID = 1L;
	
	private CalmaNotice notice;
	
	public CalmaEvent(Object source, CalmaNotice calmaNotice) {
		super(source);
		this.notice = calmaNotice;
	}
}
