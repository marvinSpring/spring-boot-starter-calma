package com.marvin.event.listener;

import com.marvin.model.CalmaNotice;
import com.marvin.util.NoticeSendComponent;

/**
 * @Describe: 将异常信息监听
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class CalmaNotifier extends AbstractCalmaNotifier{//异常监听器

	public CalmaNotifier(NoticeSendComponent<CalmaNotice> noticeSendComponent ) {
		super(noticeSendComponent);
	}

}
