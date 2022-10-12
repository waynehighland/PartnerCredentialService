package com.bt.marketplace.partnercredentials.service;

import com.bt.marketplace.partnercredentials.domain.EncryptedInfo;
import com.bt.marketplace.partnercredentials.domain.PartnerCredentialDocument;
import com.bt.marketplace.partnercredentials.exceptions.DocumentNotFoundException;
import com.bt.marketplace.partnercredentials.exceptions.ProcessingException;
import com.bt.marketplace.partnercredentials.model.Credentials;
import com.bt.marketplace.partnercredentials.model.OrganisationSecrets;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialRequest;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialResponse;
import com.bt.marketplace.partnercredentials.repository.CredentialRepository;
import com.bt.marketplace.partnercredentials.service.encryption.EncryptionService;
import com.bt.marketplace.partnercredentials.service.utils.JsonHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.vault.VaultException;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CredentialServiceImpl implements PartnerCredentialService {
    private final EncryptionService encryptionService;
    private final CredentialRepository repository;

    public PartnerCredentialResponse getCredentialDetails(String customerId, String isvId)  {
        Optional<PartnerCredentialDocument> document = repository.findByCustomerIdAndIsvId(customerId, isvId);
        return document.isPresent() ? createResponse(document.get()) : null;
    }

    public void saveCredentials(PartnerCredentialRequest request) {
        try {
            JsonHelper jsonHelper = new JsonHelper();

            Credentials creds = request.getCredentials();
            OrganisationSecrets secrets = creds.getOrganisationSecrets();

            EncryptedInfo encryptedInfo = EncryptedInfo.builder()
                    .clientSecret(secrets.getClientSecret())
                    .accessToken(secrets.getAccessToken())
                    .clientId(secrets.getClientId())
                    .refreshToken(secrets.getRefreshToken())
                    .build();

            repository.save(PartnerCredentialDocument.builder()
                             .customerId(request.getCustomerId())
                             .isvId(request.getIsvId())
                             .tenetId(creds.getTenetId())
                             .userId(creds.getUserId())
                             .encrypted(encryptionService.encrypt(jsonHelper.convertToJson(encryptedInfo)))
                             .build());

        } catch (VaultException exception) {
            log.error("Error using the vault encryption service: " + exception.getLocalizedMessage());
            throw new ProcessingException(exception);
        }
    }


    private PartnerCredentialResponse createResponse(PartnerCredentialDocument document) {
        if (document == null) {
            return null;
        }

        EncryptedInfo sensitive = JsonHelper.convertToObject(encryptionService.decrypt(document.getEncrypted()));
        PartnerCredentialResponse response = null;
        try {
            response = PartnerCredentialResponse.builder()
                    .customerId(document.getCustomerId())
                    .isvId(document.getIsvId())
                    .credentials(Credentials.builder()
                    .userId(document.getUserId())
                    .tenetId(document.getTenetId())
                    .organisationSecrets( OrganisationSecrets.builder()
                    .accessToken(sensitive.getAccessToken())
                    .clientId(sensitive.getClientId())
                    .refreshToken(sensitive.getRefreshToken())
                    .build()).build())
                    .build();
        }catch (Exception e) {
            log.error("Unable to decrypt response: " + e.getMessage());
            throw new ProcessingException(e);
        }
        return response;
    }
}
