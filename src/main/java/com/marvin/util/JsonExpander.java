package com.marvin.util;

import com.google.gson.Gson;

import feign.Param.Expander;

public class JsonExpander implements Expander{

	private final Gson gson = new Gson();
	
	@Override
	public String expand(Object value) {
		return gson.toJson(value);
	}

}
