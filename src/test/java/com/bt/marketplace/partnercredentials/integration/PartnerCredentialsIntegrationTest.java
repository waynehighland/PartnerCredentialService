package com.bt.marketplace.partnercredentials.integration;

import static com.bt.marketplace.partnercredentials.controller.common.ValidationMessage.*;
import static com.bt.marketplace.partnercredentials.controller.common.ValidationMessage.MSG_DSCR_MUST_NOT_BE_EMPTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Optional;
import java.util.Set;

import com.bt.marketplace.partnercredentials.model.Credentials;
import com.bt.marketplace.partnercredentials.model.OrganisationSecrets;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialRequest;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.bt.marketplace.partnercredentials.integration.helper.PartnerCredentialsIntegrationTestHelper;
import com.bt.marketplace.partnercredentials.domain.PartnerCredentialDocument;
import com.bt.marketplace.partnercredentials.repository.CredentialRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

@Testcontainers
@DataMongoTest
public class PartnerCredentialsIntegrationTest {
    private final String[] allfields = {
            FIELD_NAME_CUSTOMER_ID, FIELD_NAME_ISV_ID, FIELD_NAME_TENET_ID,FIELD_NAME_CLIENT_ID,
            FIELD_NAME_USER_ID, FIELD_NAME_CLIENT_SECRET, FIELD_NAME_ACCESS_TOKEN, FIELD_NAME_REFRESH_TOKEN};

	@Autowired
    private CredentialRepository credentialsRepository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	private PartnerCredentialsIntegrationTestHelper helper = new PartnerCredentialsIntegrationTestHelper(validator);

	@Container
    public static MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:4.4.3"));

    @BeforeAll
    static void initAll(){
        container.start();
    }

    @Test
    @Disabled
    void savePartnerCredentials() {
        PartnerCredentialDocument request = helper.partnerCredentialTestData();
    	PartnerCredentialDocument partnerCredentials = credentialsRepository.save(request);
    	Optional<PartnerCredentialDocument> retrievePartnerCredentials = credentialsRepository.findByCustomerIdAndIsvId(request.getCustomerId(), request.getIsvId());
		assertThat(partnerCredentials).isEqualTo(retrievePartnerCredentials.get());
    }
    
    @Test
    @Disabled
    void getPartnerCredentials() {
    	Optional<PartnerCredentialDocument> partnerCredentials = credentialsRepository.findByCustomerIdAndIsvId(
                                                                    PartnerCredentialsIntegrationTestHelper.CUSTOMER_ID,
                                                                    PartnerCredentialsIntegrationTestHelper.ISV_ID);
        assertThat("fseofih23oihfoddasds").contains(partnerCredentials.get().getIsvId());
    }
    
    @Test
    @Disabled
    void getPartnerCredentialsByCustomerAndIsvId() {
    	Optional<PartnerCredentialDocument> partnerCredentials = credentialsRepository.findByCustomerIdAndIsvId(PartnerCredentialsIntegrationTestHelper.CUSTOMER_ID,
    			PartnerCredentialsIntegrationTestHelper.ISV_ID);
        helper.getCredentialsAssertionValidator(partnerCredentials.get());
    }

    @Test
    void givenNullValuesForMandatoryFieldsThenRequestFailsWithViolations() {
        //given
        PartnerCredentialRequest request = PartnerCredentialRequest.builder().build();
        helper.whenMultiFieldValidationThenGivesViolation( request, new String[] {MSG_DSCR_MUST_NOT_BE_EMPTY},
                FIELD_NAME_CUSTOMER_ID, FIELD_NAME_ISV_ID );
    }

    @Test
    void givenSpacesForCustomerIdAndIsvIdThenValidationFailsWithMustNotBeEmptyViolations() {
        //given
        PartnerCredentialRequest request = PartnerCredentialRequest.builder()
                .isvId("").customerId("").credentials(Credentials.builder().organisationSecrets(OrganisationSecrets.builder().build()).build()).build();
        helper.whenMultiFieldValidationThenGivesViolation( request, new String[] {MSG_DSCR_MUST_NOT_BE_EMPTY}, allfields);
    }

    @Test
    void givenAllMandantoryFieldsValidThenPartnerCredentialValidationPasses() {
        //given
        PartnerCredentialRequest credentialRequest = PartnerCredentialRequest.builder()
                .isvId("1200202")
                .customerId("2343424rnewdsamas")
                .credentials(
                        Credentials.builder()
                                .userId("23934293432")
                                .tenetId("rewrwrwew")
                                .organisationSecrets(OrganisationSecrets.builder()
                                        .refreshToken("fkdsafkdsjhk23rk24r3243")
                                        .accessToken("rewfdf4351353134332")
                                        .clientSecret("qewrewrkeqwkr")
                                        .clientId("42341432432423")
                                        .build())
                                .build())
                .build();

        Set<ConstraintViolation<PartnerCredentialRequest>> violations = validator.validate(credentialRequest);
        MatcherAssert.assertThat(violations.size(),is(0));
    }
}
