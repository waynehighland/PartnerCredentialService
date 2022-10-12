package com.bt.marketplace.partnercredentials.service.utils;

import com.bt.marketplace.partnercredentials.domain.EncryptedInfo;
import com.bt.marketplace.partnercredentials.exceptions.ProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonHelper {
    public static EncryptedInfo convertToObject(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, EncryptedInfo.class);
        } catch (JsonProcessingException e) {
            log.error("Error while decrypting to payload to object format.");
            throw new ProcessingException(e);
        }
    }

    public String convertToJson(EncryptedInfo encryptedInfo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String payload =  mapper.writeValueAsString(encryptedInfo);
            return removeUnwantedCharactersFromJson(payload);
        } catch (JsonProcessingException e) {
            log.error("Error while converting to payload to json format.");
            throw new ProcessingException(e);
        }
    }

    public String removeUnwantedCharactersFromJson(String isonPayload) {
        if (isonPayload == null)
            return isonPayload;
        return isonPayload.replaceAll("[\\n\\r]","");
    }
}
