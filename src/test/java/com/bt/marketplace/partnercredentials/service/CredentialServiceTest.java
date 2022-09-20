package com.bt.marketplace.partnercredentials.service;

import com.bt.marketplace.partnercredentials.model.PartnerCredentialRequest;
import com.bt.marketplace.partnercredentials.repository.CredentialRepository;
import com.bt.marketplace.partnercredentials.service.encryption.EncryptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CredentialServiceTest {

    @Mock
    private CredentialRepository repository;

    @Mock
    private EncryptionService encryptionService;
    private PartnerCredentialService service;

    @BeforeEach
    void setup() {
        service = new CredentialServiceImpl(encryptionService, repository);
    }

    @Test
    public void givenCredentialsWhenSavingThenEncryptAndSave() {
        service.saveCredentials(createCredentialRequest());
    }

    private PartnerCredentialRequest createCredentialRequest() {
        return PartnerCredentialRequest.builder().build();
    }

}
