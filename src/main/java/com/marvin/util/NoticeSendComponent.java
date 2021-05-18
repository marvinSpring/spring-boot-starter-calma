package com.marvin.util;

import com.marvin.model.PiracyNotice;

/**
 * @Describe: 异常通知的发送组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@FunctionalInterface
public interface NoticeSendComponent<T extends PiracyNotice> {

	public void send(T entity);
}
