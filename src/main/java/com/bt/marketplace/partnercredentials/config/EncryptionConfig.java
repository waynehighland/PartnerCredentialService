package com.bt.marketplace.partnercredentials.config;

import com.bt.marketplace.partnercredentials.service.encryption.EncryptionService;
import com.bt.marketplace.partnercredentials.service.encryption.VaultEncryptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.core.VaultTemplate;

@Configuration
public class EncryptionConfig {

    @Bean
    public EncryptionService vaultEncryptionService(VaultConfig config, VaultTemplate template) {
        return new VaultEncryptionService(config, template);
    }
}
