package com.bt.marketplace.partnercredentials.controller;

import com.bt.marketplace.partnercredentials.model.PartnerCredentialRequest;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialResponse;
import com.bt.marketplace.partnercredentials.service.PartnerCredentialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PartnerCredentialControllerTest {

    @Mock
    private PartnerCredentialService service;

    private PartnerCredentialController controller;

    @BeforeEach
    public void setup() {
        controller = new PartnerCredentialController(service);
    }

    @Test
    public void givenValidCredentialWhenSavingThenStoreDetails() {
        service.saveCredentials(createCredentialRequest());
    }


    @Test
    public void givenInValidCredentialWhenSavingThrowException() {
        Mockito.doThrow(new IllegalArgumentException("Invalid argument")).when(service).saveCredentials(Mockito.any());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.saveCredentials(createCredentialRequest());
        });
        assertTrue(exception.getMessage().startsWith("Invalid argument"));
    }

    private PartnerCredentialResponse createCredentialResponse() {
        return PartnerCredentialResponse.builder().build();
    }

    private PartnerCredentialRequest createCredentialRequest() {
        return PartnerCredentialRequest.builder().build();
    }

}
