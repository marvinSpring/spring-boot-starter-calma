package com.marvin.util;

import com.marvin.model.Notice;

@FunctionalInterface
public interface Client {

	void doSend(Notice notice);
}
