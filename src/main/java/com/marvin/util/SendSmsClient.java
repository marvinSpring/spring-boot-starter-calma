package com.marvin.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.marvin.model.Notice;
import com.marvin.model.SmsNotice;

import java.util.Map;

//阿里云sms短信发送组件
public class SendSmsClient implements Client {

	private SmsNotice noticeStruct;

	public SendSmsClient(SmsNotice noticeStruct) {
		this.setNoticeStruct(noticeStruct);
	}

	public String send(SmsNotice notice) {
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
	public void doSend(Notice smsNotice) {
		send((SmsNotice) smsNotice);
	}

	public SmsNotice getNoticeStruct() {
		return noticeStruct;
	}

	public void setNoticeStruct(SmsNotice noticeStruct) {
		this.noticeStruct = noticeStruct;
	}
}