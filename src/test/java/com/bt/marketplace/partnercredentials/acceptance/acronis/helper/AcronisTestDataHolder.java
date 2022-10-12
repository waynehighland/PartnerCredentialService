package com.bt.marketplace.partnercredentials.acceptance.acronis.helper;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AcronisTestDataHolder {
    private final String credentialPath = "/v1/partner-credentials/store";
    private final String jsonValidationFilePath = "validation/";
    private String jsonFilePrefix = "acronis/";
    private String contentTypeJson = "application/json";
}
