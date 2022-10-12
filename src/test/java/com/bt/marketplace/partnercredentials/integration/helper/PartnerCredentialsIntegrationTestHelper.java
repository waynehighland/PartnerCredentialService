package com.bt.marketplace.partnercredentials.integration.helper;

import static org.assertj.core.api.Assertions.assertThat;

import com.bt.marketplace.partnercredentials.model.Credentials;
import com.bt.marketplace.partnercredentials.model.OrganisationSecrets;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialRequest;
import org.springframework.stereotype.Component;

import com.bt.marketplace.partnercredentials.domain.PartnerCredentialDocument;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PartnerCredentialsIntegrationTestHelper {
	private final Validator validator;
	public PartnerCredentialsIntegrationTestHelper(Validator validator) {
		this.validator = validator;
	}
	public static final String CUSTOMER_ID = "fseofih23oihfodsif2oihf";
	public static final String ISV_ID = "fseofih23oihfoddasds";
	
	public PartnerCredentialDocument partnerCredentialTestData() {
		return PartnerCredentialDocument.builder()
        		.customerId("fseofih23oihfodsif2oihf")
        		.isvId("fseofih23oihfoddasds")
				.tenetId("acronis")
        		.encrypted("EQAAEJW2HwG3__sdC9KgpF7uraZa-jFL_PEjCY")
        		.build();
	}
	
	public void getCredentialsAssertionValidator(PartnerCredentialDocument partnerCredentials) {
		assertThat(ISV_ID).contains(partnerCredentials.getIsvId());
        assertThat("EQAAEJW2HwG3__sdC9KgpF7uraZa-jFL_PEjCY").contains(partnerCredentials.getEncrypted());
 	}

	 public void whenMultiFieldValidationThenGivesViolation( PartnerCredentialRequest credentialRequest,
															 String[] validationMessages, String... fields) {
		Set<ConstraintViolation<PartnerCredentialRequest>> violations = validator.validate(credentialRequest);
		assertThat(violations.stream().map( x -> x.getPropertyPath().toString()).collect(Collectors.toSet()))
				.containsExactlyInAnyOrder(fields);

//		Set<String> messages = violations.stream().map(x -> x.getPropertyPath().toString()).collect(Collectors.toSet());
		assertThat(violations.stream().map(x -> x.getMessage()).collect(Collectors.toSet()))
				.containsExactlyInAnyOrder(validationMessages);
	 }
}
