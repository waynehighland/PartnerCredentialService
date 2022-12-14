package com.bt.marketplace.partnercredentials.service.encryption;

import com.bt.marketplace.partnercredentials.config.VaultConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.VaultTransitOperations;

@Slf4j
public class VaultEncryptionService implements EncryptionService {
    private VaultConfig config;
    private VaultTemplate vaultTemplate;

    public VaultEncryptionService( VaultConfig config, VaultTemplate vaultTemplate) {
        this.config = config;
        this.vaultTemplate = vaultTemplate;
    }

    @Override
    public String encrypt(String plainText) {
        VaultTransitOperations transitOperations = vaultTemplate.opsForTransit();
        String cipherText = transitOperations.encrypt(config.getEncryptionKey(), plainText);
        log.info("Current text:" + plainText + " cipher:" + cipherText);
        return cipherText;
    }

    @Override
    public String decrypt(String cipherText) {
        VaultTransitOperations transitOperations = vaultTemplate.opsForTransit();
        String plainText = transitOperations.decrypt(config.getEncryptionKey(), cipherText);
        log.info("Cipher text:" + cipherText + " plain:" + plainText);
        return plainText;
    }
}
