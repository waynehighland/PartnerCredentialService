package com.bt.marketplace.partnercredentials.acceptance.acronis;

import com.bt.marketplace.partnercredentials.acceptance.acronis.base.AcronisAcceptanceTestBase;
import com.bt.marketplace.partnercredentials.controller.common.error.ApiErrors;
import com.bt.marketplace.partnercredentials.controller.common.error.ErrorRepresentation;
import com.bt.marketplace.partnercredentials.model.Credentials;
import com.bt.marketplace.partnercredentials.model.OrganisationSecrets;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PartnerCredentialValidationAcceptanceTest extends AcronisAcceptanceTestBase {
    @Test
    void testGivenInvalidFieldsThenPartnerCreationRejectedWithValidationErrors() {
        PartnerCredentialRequest invalidCredentialRequest = PartnerCredentialRequest.builder()
                .customerId("324342").credentials(Credentials.builder()
                        .tenetId("324234")
                        .userId("42342343")
                        .organisationSecrets(OrganisationSecrets.builder()
                                .clientId("2342334")
                                .accessToken("r5r435r2345r435")
                                .refreshToken("43r143243243234")
                                .build())
                        .build())
                .build();

        when.callCredentialApiAndGetResponse(invalidCredentialRequest).consumeWith(result -> {
            List<ErrorRepresentation> apiErrors = result.getResponseBody().getErrors();
            ApiErrors errorsList = jsonReader.errorsFromClassPathFile(testData.getJsonFilePrefix() + testData.getJsonValidationFilePath() +
                    "AcronisInvalidFieldsValidation.json");
            assertThat(apiErrors.stream()).containsExactlyInAnyOrderElementsOf(errorsList.getErrors());
        });

    }
}
