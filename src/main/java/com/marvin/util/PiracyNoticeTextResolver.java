package com.marvin.util;

import com.marvin.model.PiracyNotice;

@FunctionalInterface
public interface PiracyNoticeTextResolver<T extends PiracyNotice> {

	public String resolve(T exceptionNotice);
}
