package com.marvin.listener;

import org.springframework.context.ApplicationListener;
import com.marvin.event.PiracyListEvent;
import com.marvin.model.PiracyNotice;
import com.marvin.util.DefaultDingdingClient;
import com.marvin.util.DingdingClient;
import com.marvin.util.NoticeSendComponent;
import com.marvin.util.PiracyNoticeTextResolver;
import com.marvin.util.SmsNoticeSendComponent;
import com.marvin.util.sms.SendSmsClient;

public class PiracyNotifier implements ApplicationListener<PiracyListEvent>{//异常监听器——实现梦的地方
	
	private final NoticeSendComponent<PiracyNotice> noticeSendComponent;//发送通知的组件
	
	private PiracyNoticeTextResolver<PiracyNotice> resolver  = new PiracyNoticeTextResolver<PiracyNotice>() {
		@Override
		public String resolve(PiracyNotice exceptionNotice) {
			String createText = exceptionNotice.createText();
			return createText;
		}
	};//解析并返回异常信息
	
	private DingdingClient dingClient = new DefaultDingdingClient();
	
	private SendSmsClient smsClient = new SendSmsClient();

	public PiracyNotifier(NoticeSendComponent<PiracyNotice> dingNoticeSendComponent ) {
//		if(xxx) {
//			this.noticeSendComponent = new DingNoticeSendComponent<PiracyNotice>(resolver, dingClient);
//		}else {
			this.noticeSendComponent = new SmsNoticeSendComponent<PiracyNotice>(resolver, smsClient);
//		}
	}
	
	@Override
	public void onApplicationEvent(PiracyListEvent event) {//PiracyListEvent事件发布到applicationContext后这个方法就开始跑了
		if(event instanceof PiracyListEvent) {
			send(event.getNotice());
		}
	}
	
	private void send(PiracyNotice notice) {
		noticeSendComponent.send(notice);
	}
}
