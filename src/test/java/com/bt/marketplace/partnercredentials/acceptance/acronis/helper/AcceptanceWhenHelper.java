package com.bt.marketplace.partnercredentials.acceptance.acronis.helper;

import com.bt.marketplace.partnercredentials.config.EncryptionConfig;
import com.bt.marketplace.partnercredentials.controller.common.error.ApiErrors;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient;


@Component
public class AcceptanceWhenHelper {

    private final WebTestClient webClient;
    private final EncryptionConfig config;

    private final AcronisTestDataHolder testData;

    private String base;

    public AcceptanceWhenHelper(EncryptionConfig config, WebTestClient testClient, AcronisTestDataHolder testData){
        this.webClient = testClient;
        this.config = config;
        this.testData = testData;
    }
    public WebTestClient.BodySpec<ApiErrors, ?> callCredentialApiAndGetResponse(PartnerCredentialRequest invalidCredentialRequest) {
        return webClient.post().uri(base + testData.getCredentialPath()).bodyValue(invalidCredentialRequest)
                .exchange().expectHeader().contentType(MediaType.APPLICATION_JSON).expectStatus().isBadRequest()
                .expectBody(ApiErrors.class);
    }

    public void setBase(String base) {
        this.base = base;
    }
}
