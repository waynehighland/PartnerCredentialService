package com.bt.marketplace.partnercredentials.config;

import com.bt.marketplace.partnercredentials.service.encryption.CredentialEncryption;
import com.bt.marketplace.partnercredentials.service.encryption.EncryptionService;
import com.bt.marketplace.partnercredentials.service.encryption.VaultEncryptionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.core.VaultTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    @ConditionalOnProperty(prefix = "app", name = "encryption-service", havingValue = "vault")
    public EncryptionService vaultEncryptionService(VaultConfig config, VaultTemplate template) {
        return new VaultEncryptionService(config, template);
    }

    @Bean
    @ConditionalOnProperty(prefix = "app", name = "encryption-service", havingValue = "credentials")
    public EncryptionService credentialEncryptionService(VaultConfig config) {
        return new CredentialEncryption(config);
    }
}
