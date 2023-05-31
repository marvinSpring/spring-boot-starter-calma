package com.marvin.context.support.component;

import com.marvin.model.notice.CommonNotice;

/**
 * @Describe: 异常通知的发送组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@FunctionalInterface
public interface NoticeSendComponent<T extends CommonNotice> {

	public void send(T entity);
}
