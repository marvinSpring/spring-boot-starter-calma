package com.marvin.listener;

import com.marvin.model.PiracyNotice;
import com.marvin.util.Client;
import com.marvin.util.DefaultDingdingClient;
import com.marvin.util.DingNoticeSendComponent;
import com.marvin.util.NoticeSendComponent;
import com.marvin.util.PiracyNoticeTextResolver;
import com.marvin.util.SmsNoticeSendComponent;
import com.marvin.util.sms.SendSmsClient;

/**
 * @Describe: 将异常信息监听
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public class PiracyNotifier extends AbstractPiracyNotifier{//异常监听器

	public PiracyNotifier(NoticeSendComponent<PiracyNotice> noticeSendComponent ) {
		super(noticeSendComponent);
	}

}
