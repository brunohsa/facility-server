package br.com.facility.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private ObjectMapper mapper;

    public <T> T convertJsonToObject(String json, Class<T> type) {
        try {
            mapper = new ObjectMapper();
            return mapper.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convertObjectToJson(Object object) {
        try {
            mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
