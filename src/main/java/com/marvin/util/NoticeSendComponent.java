package com.marvin.util;

import com.marvin.model.PiracyNotice;

@FunctionalInterface
public interface NoticeSendComponent<T extends PiracyNotice> {

	public void send(T entity);
}
