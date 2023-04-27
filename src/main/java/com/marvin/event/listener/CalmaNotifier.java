package com.marvin.event.listener;

import com.marvin.model.notice.CommonNotice;
import com.marvin.context.component.NoticeSendComponent;

/**
 * @Describe: 将异常信息监听
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class CalmaNotifier extends AbstractCalmaNotifier{//异常监听器

	public CalmaNotifier(NoticeSendComponent<CommonNotice> noticeSendComponent ) {
		super(noticeSendComponent);
	}

}
