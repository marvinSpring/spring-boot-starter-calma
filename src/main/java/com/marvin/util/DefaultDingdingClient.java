package com.marvin.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import com.google.gson.Gson;
import com.marvin.feign.DingdingClientFeign;
import com.marvin.model.DingdingNotice;

import feign.Feign;
import feign.FeignException;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;

public class DefaultDingdingClient implements DingdingClient {

	private DingdingClientFeign client = Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder())
			.target(DingdingClientFeign.class,"https://oapi.dingtalk.com/robot");

	private Gson gson = new Gson();
	
	private DingDingProperty dingProperty = new DingDingProperty(new String [] {"19991962259"},"ac39ef481aff92d19dc21ec6df05e5f2b2a6870e6046f3f95beb5b34110e2a3e","SECb202b52aedf7d14fb7f2d40bf76625aef65a64fb1e54d1c82f9aa84cbb6a8077");

	public DefaultDingdingClient() {
	}

	@Override
	public void doSend(DingdingNotice body) {
		HashMap<String, Object> map = new HashMap<>();
		long timeStamp = System.currentTimeMillis();
		map.put("sign", generateSign(timeStamp,dingProperty.getSignSecret()));
		map.put("timestamp",timeStamp);
		Object post = client.post(dingProperty.getAccessToken(), body, map);
	}

	private String generateSign(long timeStamp, String signSecret) {
		String strForSign = String.format("%d\n%s",timeStamp,signSecret);
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			try {
				mac.init(new SecretKeySpec(signSecret.getBytes("UTF-8"),"HmacSHA256"));
				byte[] signData = mac.doFinal(strForSign.getBytes("UTF-8"));
				return Base64.encodeBase64String(signData);
			} catch (InvalidKeyException | UnsupportedEncodingException e) {
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
