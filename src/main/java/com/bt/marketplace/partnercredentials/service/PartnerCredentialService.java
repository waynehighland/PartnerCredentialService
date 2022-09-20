package com.bt.marketplace.partnercredentials.service;

import com.bt.marketplace.partnercredentials.model.PartnerCredentialRequest;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialResponse;

public interface PartnerCredentialService {
    PartnerCredentialResponse getCredentialDetails(String customerId);
    void saveCredentials(PartnerCredentialRequest request);
}
