package br.com.facility.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.io.IOException;

public class JsonUtil {

	public static <T> T convertJsonToObject(String json, Class<T> type) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, type);
	}

	public static String convertObjectToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		configureSerializationDates(mapper);
		return mapper.writeValueAsString(object);
	}

	private static void configureSerializationDates(ObjectMapper mapper){
		mapper.registerModule(new JSR310Module());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

}