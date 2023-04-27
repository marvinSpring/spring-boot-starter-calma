package com.marvin.util;

import java.util.Map;

import com.marvin.model.message.DingMessage;
import com.marvin.util.JsonExpander;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

public interface DingClientFeign {

	@RequestLine("POST /send?access_token={accessToken}")  
	@Headers("Content-Type: application/json; charset=utf-8")
	@Body("{body}")
	Object post(@Param("accessToken") String accessToken,
			@Param(value = "body", expander = JsonExpander.class) DingMessage body,
			@QueryMap Map<String, Object> map);//调钉钉机器人的接口

}
