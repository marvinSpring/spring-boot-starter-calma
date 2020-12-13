package com.marvin.util.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.marvin.model.DingdingNotice;
import com.marvin.model.Notice;
import com.marvin.model.PiracyNotice;
import com.marvin.model.SmsNotice;
import com.marvin.util.Client;

//阿里云sms短信发送组件
public class SendSmsClient implements Client{
	
	private SmsNotice noticeStruct;
	
    public SendSmsClient(SmsNotice noticeStruct) {
		this.noticeStruct = noticeStruct;
	}

	public String send(SmsNotice noticeStruct) {
        DefaultProfile profile = DefaultProfile.getProfile(noticeStruct.getRegionId(), noticeStruct.getAccessKey(),noticeStruct.getSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId",noticeStruct.getRegionId());
        request.putQueryParameter("PhoneNumbers", noticeStruct.getPhoneNumbers());//手机号
        request.putQueryParameter("SignName", noticeStruct.getSignName());//签名
        request.putQueryParameter("TemplateCode", noticeStruct.getTemplateCode());//模板码
        request.putQueryParameter("TemplateParam", "{\"name\":\"Marvin\",\"code\":\""+noticeStruct.getParam()+"\"}");//参数 TODO:这里只能等通用模板签名申请下来才可以自定义短信内容
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            return response.getData();
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
        return null;
    }

	@Override
	public void doSend(Notice smsNotice) {
		send((SmsNotice)smsNotice);
	}

 
}