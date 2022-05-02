package com.marvin.util.client;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.marvin.model.send.ExceptionSendContext;
import com.marvin.model.send.SmsExceptionSendContext;

import java.util.Map;

//阿里云sms短信发送组件
public class SendSmsClient implements Client {

	private SmsExceptionSendContext noticeStruct;

	public SendSmsClient(SmsExceptionSendContext noticeStruct) {
		this.setNoticeStruct(noticeStruct);
	}

	public String send(SmsExceptionSendContext notice) {
		DefaultProfile profile = DefaultProfile.getProfile(noticeStruct.getRegionId(), noticeStruct.getAccessKey(),
				noticeStruct.getSecret());
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setSysMethod(MethodType.POST);
		request.setSysDomain("dysmsapi.aliyuncs.com");
		request.setSysVersion("2017-05-25");
		request.setSysAction("SendSms");
		request.putQueryParameter("RegionId", this.noticeStruct.getRegionId());
		request.putQueryParameter("PhoneNumbers", this.noticeStruct.getPhoneNumbers());// 手机号
		request.putQueryParameter("SignName", this.noticeStruct.getSignName());// 签名
		request.putQueryParameter("TemplateCode", this.noticeStruct.getTemplateCode());// 模板码
		Map<String, Object> param = notice.getParam();
		request.putQueryParameter("TemplateParam",
				"{\"project\":\"" + param.get("project") + "\",\"time\":\""
						+ param.get("time").toString().replaceAll("T"," ").subSequence(0, param.get("time").toString().length() - 7)
						+ "\",\"causeBy\":\"" + param.get("causeBy") + "\"}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			return response.getData();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void doSend(ExceptionSendContext smsNotice) {
		send((SmsExceptionSendContext) smsNotice);
	}

	public SmsExceptionSendContext getNoticeStruct() {
		return noticeStruct;
	}

	public void setNoticeStruct(SmsExceptionSendContext noticeStruct) {
		this.noticeStruct = noticeStruct;
	}
}