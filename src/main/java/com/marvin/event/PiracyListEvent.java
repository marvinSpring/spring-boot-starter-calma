package com.marvin.event;

import org.springframework.context.ApplicationEvent;

import com.marvin.model.PiracyNotice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PiracyListEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	
	private PiracyNotice notice;
	
	public PiracyListEvent(Object source, PiracyNotice mailNotice) {
		super(source);
		this.notice = mailNotice;
	}

	public void print() {
		System.err.println("Hello Spring Event");
	}
	
}
