package com.bt.marketplace.partnercredentials.service;

import com.bt.marketplace.partnercredentials.model.PartnerCredentialDocument;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialRequest;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialResponse;
import com.bt.marketplace.partnercredentials.model.SensitiveData;
import com.bt.marketplace.partnercredentials.repository.CredentialRepository;
import com.bt.marketplace.partnercredentials.service.encryption.EncryptionService;
import com.bt.marketplace.partnercredentials.service.utils.DocumentHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
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
        SensitiveData sensitiveData = null;
        try {

            if(document.getSensitiveData() != null ) {
                sensitiveData =DocumentHelper.convertToSensitiveData(encryptionService.decrypt(document.getSensitiveData()));
            } else {
                throw new RuntimeException("Invalid sensitive data");
            }

            response = PartnerCredentialResponse.builder()
                    .accessToken(sensitiveData.getAccessToken())
                    .refreshToken(sensitiveData.getRefreshToken())
                    .tenetId(sensitiveData.getTenetId())
                    .isvId(document.getIsvId())
                    .locationId(document.getLocationId())
                    .clientId(sensitiveData.getClientId())
                    .customerId(document.getCustomerId())
                    .build();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public void saveCredentials(PartnerCredentialRequest request) {
        try {
            SensitiveData data = SensitiveData.builder()
                    .accessToken(request.getAccessToken())
                    .refreshToken(request.getRefreshToken())
                    .tenetId(request.getTenetId())
                    .clientId(request.getClientId())
                    .build();

            PartnerCredentialDocument document = PartnerCredentialDocument.builder()
                    .customerId(request.getCustomerId())
                    .isvId(request.getIsvId())
                    .locationId(request.getLocationId())
                    .sensitiveData(encryptionService.encrypt(DocumentHelper.convertToString(data)))
                    .build();
            repository.save(document);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
