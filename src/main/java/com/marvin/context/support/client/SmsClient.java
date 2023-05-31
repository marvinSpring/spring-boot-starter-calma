package com.marvin.context.support.client;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.marvin.model.message.Message;
import com.marvin.model.message.SmsMessage;

import java.util.Map;

//阿里云sms短信发送组件
public class SmsClient implements Client {

	private SmsMessage smsMessage;

	public SmsClient(SmsMessage smsMessage) {
		this.setNoticeStruct(smsMessage);
	}

	public String send(SmsMessage notice) {
		DefaultProfile profile = DefaultProfile.getProfile(smsMessage.getRegionId(), smsMessage.getAccessKey(),
				smsMessage.getSecret());
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setSysMethod(MethodType.POST);
		request.setSysDomain("dysmsapi.aliyuncs.com");
		request.setSysVersion("2017-05-25");
		request.setSysAction("SendSms");
		request.putQueryParameter("RegionId", this.smsMessage.getRegionId());
		request.putQueryParameter("PhoneNumbers", this.smsMessage.getPhoneNumbers());// 手机号
		request.putQueryParameter("SignName", this.smsMessage.getSignName());// 签名
		request.putQueryParameter("TemplateCode", this.smsMessage.getTemplateCode());// 模板码
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
	public void doSend(Message smsMessage) {
		send((SmsMessage) smsMessage);
	}

	public SmsMessage getNoticeStruct() {
		return smsMessage;
	}

	public void setNoticeStruct(SmsMessage smsMessage) {
		this.smsMessage = smsMessage;
	}
}