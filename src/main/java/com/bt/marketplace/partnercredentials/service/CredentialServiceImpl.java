package com.bt.marketplace.partnercredentials.service;

import com.bt.marketplace.partnercredentials.model.PartnerCredentialDocument;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialRequest;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialResponse;
import com.bt.marketplace.partnercredentials.repository.CredentialRepository;
import com.bt.marketplace.partnercredentials.service.encryption.EncryptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CredentialServiceImpl implements PartnerCredentialService {
    private final EncryptionService encryptionService;
    private final CredentialRepository repository;

    public PartnerCredentialResponse getCredentialDetails(String customerId) {
        Optional<PartnerCredentialDocument> document = repository.findById(customerId);
        return document.isPresent() ? createCredentialResponse(document.get()) : null;
    }

    private PartnerCredentialResponse createCredentialResponse(PartnerCredentialDocument document) {
        PartnerCredentialResponse response = null;
        try {
            response = PartnerCredentialResponse.builder()
                    .accessToken(encryptionService.decrypt(document.getAccessToken()))
                    .refreshToken(encryptionService.decrypt(document.getRefreshToken()))
                    .tenetId(encryptionService.decrypt(document.getTenetId()))
                    .isvId(document.getIsvId())
                    .locationId(document.getLocationId())
                    .clientId(encryptionService.decrypt(document.getClientId()))
                    .customerId(document.getCustomerId())
                    .build();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public void saveCredentials(PartnerCredentialRequest request) {
        try {
            PartnerCredentialDocument document = PartnerCredentialDocument.builder()
                    .customerId(request.getCustomerId())
                    .isvId(request.getIsvId())
                    .locationId(request.getLocationId())
                    .tenetId(encryptionService.encrypt(request.getTenetId()))
                    .accessToken(encryptionService.encrypt(request.getAccessToken()))
                    .refreshToken(encryptionService.encrypt(request.getRefreshToken()))
                    .clientId(encryptionService.encrypt(request.getClientId()))
                    .build();
            repository.save(document);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
