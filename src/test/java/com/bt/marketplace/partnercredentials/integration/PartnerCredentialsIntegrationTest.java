package com.bt.marketplace.partnercredentials.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.bt.marketplace.partnercredentials.integration.helper.PartnerCredentialsIntegrationTestHelper;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialDocument;
import com.bt.marketplace.partnercredentials.repository.CredentialRepository;

@Testcontainers
@DataMongoTest
public class PartnerCredentialsIntegrationTest {

	@Autowired
    private CredentialRepository credentialsRepository;
	
	private PartnerCredentialsIntegrationTestHelper helper = new PartnerCredentialsIntegrationTestHelper();

	@Container
    public static MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:4.4.3"));

    @BeforeAll
    static void initAll(){
        container.start();
    }

    @Test
    void savePartnerCredentials() {
    	helper.partnerCredentialTestData();
    	PartnerCredentialDocument partnerCredentials = credentialsRepository.save(helper.partnerCredentialTestData());
    	Optional<PartnerCredentialDocument> retrievePartnerCredentials = credentialsRepository.findById(partnerCredentials.getCustomerId());
		assertThat(partnerCredentials).isEqualTo(retrievePartnerCredentials.get());
    }
    
    @Test
    void getPartnerCredentials() {
    	Optional<PartnerCredentialDocument> partnerCredentials = credentialsRepository.findById(PartnerCredentialsIntegrationTestHelper.CUSTOMER_ID);
        assertThat("fseofih23oihfoddasds").contains(partnerCredentials.get().getIsvId());
    }
    
    @Test
    void getPartnerCredentialsByCustomerAndIsvId() {
    	PartnerCredentialDocument partnerCredentials = credentialsRepository.findByCustomerIdAndIsvId(PartnerCredentialsIntegrationTestHelper.CUSTOMER_ID,
    			PartnerCredentialsIntegrationTestHelper.ISV_ID);
        helper.getCredentialsAssertionValidator(partnerCredentials);
    }
}
