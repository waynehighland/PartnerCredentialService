package com.bt.marketplace.partnercredentials.service.utils;

import com.bt.marketplace.partnercredentials.model.SensitiveData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DocumentHelper {
    public static String convertToJsonForEncryption(SensitiveData data)  {
        String response = null;
        ObjectMapper mapper = getObjectMapper();
        try {
            response =  mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return removeUnwantedCharactersForEncryption(response);
    }

    private static String removeUnwantedCharactersForEncryption(String jsonString) {
        return jsonString.replaceAll("[\\n\\r\\t]","");
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

    public static SensitiveData convertToSensitiveData(String sensitiveData) {
        SensitiveData response = null;
        ObjectMapper mapper = getObjectMapper();
        try {
            response =  mapper.readValue(sensitiveData, SensitiveData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
