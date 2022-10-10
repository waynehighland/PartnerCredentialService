package com.bt.marketplace.partnercredentials.integration.helper;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.stereotype.Component;

import com.bt.marketplace.partnercredentials.model.PartnerCredentialDocument;

@Component
public class PartnerCredentialsIntegrationTestHelper {

	public static final String CUSTOMER_ID = "fseofih23oihfodsif2oihf";
	public static final String ISV_ID = "fseofih23oihfoddasds";
	
	public PartnerCredentialDocument partnerCredentialTestData() {
		return PartnerCredentialDocument.builder()
        		.customerId("fseofih23oihfodsif2oihf")
        		.isvId("fseofih23oihfoddasds")
        		.locationId("c7e565-f1222")
        		.accessToken("EQAAEJW2HwG3__sdC9KgpF7uraZa-jFL_PEjCY")
        		.refreshToken("EAAAEJEBYTM-VW7mcy79SVS-ZXN7f6tnozmi_WpX1CiQxh")
        		.build();
	}
	
	public void getCredentialsAssertionValidator(PartnerCredentialDocument partnerCredentials) {
		assertThat(ISV_ID).contains(partnerCredentials.getIsvId());
        assertThat("EQAAEJW2HwG3__sdC9KgpF7uraZa-jFL_PEjCY").contains(partnerCredentials.getAccessToken());
        assertThat("EAAAEJEBYTM-VW7mcy79SVS-ZXN7f6tnozmi_WpX1CiQxh").contains(partnerCredentials.getRefreshToken());
 
	}

}
