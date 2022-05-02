package com.marvin.util;

import com.marvin.model.CalmaNotice;
import com.marvin.model.Notice;

/**
 * @Describe: 异常通知的发送组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@FunctionalInterface
public interface NoticeSendComponent<T extends CalmaNotice> {

	public void send(T entity);
}
