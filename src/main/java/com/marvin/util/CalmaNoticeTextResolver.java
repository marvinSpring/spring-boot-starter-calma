package com.marvin.util;

import com.marvin.model.loader.AbstractExceptionLoader;

@FunctionalInterface
public interface CalmaNoticeTextResolver<T extends AbstractExceptionLoader> {

	public String resolve(T exceptionNotice);
}
