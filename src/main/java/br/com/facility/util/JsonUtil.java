package br.com.facility.util;

import br.com.facility.exceptions.InternalServerErrorException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.io.IOException;

public class JsonUtil {

	private JsonUtil() {
	}

	public static <T> T convertJsonToObject(String json, Class<T> type) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json, type);
		} catch (IOException e) {
			throw new InternalServerErrorException("Erro ao converter o model " + json + "para objeto : " + e.getMessage());
		}
	}

	public static String convertObjectToJson(Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			configureSerializationDates(mapper);
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new InternalServerErrorException("Erro ao converter o objeto para model : " + e.getMessage());
		}
	}

	private static void configureSerializationDates(ObjectMapper mapper) {
		mapper.registerModule(new JSR310Module());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

}