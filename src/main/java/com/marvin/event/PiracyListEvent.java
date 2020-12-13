package com.marvin.event;

import org.springframework.context.ApplicationEvent;

import com.marvin.model.PiracyNotice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PiracyListEvent extends ApplicationEvent{//异常事件驱动器
	
	private static final long serialVersionUID = 1L;
	
	private PiracyNotice notice;
	
	public PiracyListEvent(Object source, PiracyNotice piracyNotice) {
		super(source);
		this.notice = piracyNotice;
	}
}
