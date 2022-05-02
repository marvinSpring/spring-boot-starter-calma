package com.marvin.util.component;

import com.marvin.model.loader.AbstractExceptionLoader;

/**
 * @Describe: 异常通知的发送组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@FunctionalInterface
public interface NoticeSendComponent<T extends AbstractExceptionLoader> {

	public void send(T entity);
}
