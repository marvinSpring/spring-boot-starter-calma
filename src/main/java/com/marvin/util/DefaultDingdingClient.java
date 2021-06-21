package com.marvin.util;

import com.google.gson.Gson;
import com.marvin.feign.DingdingClientFeign;
import com.marvin.model.DingdingNotice;
import com.marvin.model.Notice;
import feign.Feign;
import feign.FeignException;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Slf4j
public class DefaultDingdingClient implements Client {// 发送钉钉通知的客户端

	private final DingdingClientFeign client = Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder()).target(DingdingClientFeign.class, "https://oapi.dingtalk.com/robot");

	private final Gson gson = new Gson();// json

	private final DingDingProperty dingProperty;

	public DefaultDingdingClient(DingDingProperty dingProperty) {
		this.dingProperty = dingProperty;
	}

	@Override // 预请求钉钉接口 
	public void doSend(Notice body) {
		long timeStamp = System.currentTimeMillis();
		HashMap<String, Object> map = new HashMap<>();
		map.put("sign", generateSign(timeStamp, dingProperty.getSecret()));
		map.put("timestamp", timeStamp);
		client.post(dingProperty.getAccess_token(), (DingdingNotice) body, map);
	}

	private String generateSign(long timeStamp, String signSecret) {// 生成sign码
		String strForSign = String.format("%d\n%s", timeStamp, signSecret);
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			try {
				mac.init(new SecretKeySpec(signSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
				byte[] signData = mac.doFinal(strForSign.getBytes(StandardCharsets.UTF_8));
				return Base64.encodeBase64String(signData);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	class GsonDecoder implements Decoder {

		@Override
		public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
			return gson.fromJson(response.body().asReader(), type);
		}

	}

	class GsonEncoder implements Encoder {

		@Override
		public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
			template.body(gson.toJson(object).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
		}

	}

}
