package com.bt.marketplace.partnercredentials.acceptance.base.error.helper;

import com.bt.marketplace.partnercredentials.controller.common.error.ApiErrors;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class JsonFileReader {
    private final String FILES_FOLDER = "__files/";
    public ApiErrors errorsFromClassPathFile(String fileName) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream( FILES_FOLDER + fileName);
        if ( is == null) return null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(is, new TypeReference<ApiErrors>() {});
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
