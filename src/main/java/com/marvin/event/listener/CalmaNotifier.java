package com.marvin.event.listener;

import com.marvin.model.loader.SmartExceptionLoader;
import com.marvin.util.component.NoticeSendComponent;

/**
 * @Describe: 将异常信息监听
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class CalmaNotifier extends AbstractCalmaNotifier{//异常监听器

	public CalmaNotifier(NoticeSendComponent<SmartExceptionLoader> noticeSendComponent ) {
		super(noticeSendComponent);
	}

}
