package com.marvin.util;

import com.marvin.model.DingdingNotice;

@FunctionalInterface
public interface DingdingClient {

	void doSend(DingdingNotice dingNotice);
}
