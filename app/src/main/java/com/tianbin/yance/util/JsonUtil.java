package com.tianbin.yance.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static <T> T parseResult(String context, Class<T> valueType) {
		T readValue = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			readValue = objectMapper.readValue(context, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readValue;
	}
}
