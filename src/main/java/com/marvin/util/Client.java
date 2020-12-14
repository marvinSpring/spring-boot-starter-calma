package com.marvin.util;

import com.marvin.model.DingdingNotice;
import com.marvin.model.Notice;
import com.marvin.model.PiracyNotice;

@FunctionalInterface
public interface Client {

	void doSend(Notice notice);
}
