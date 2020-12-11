package com.marvin.util;

import com.marvin.model.DingdingNotice;
import com.marvin.model.DingdingResult;

@FunctionalInterface
public interface DingdingClient {

	void doSend(DingdingNotice dingNotice);
}
