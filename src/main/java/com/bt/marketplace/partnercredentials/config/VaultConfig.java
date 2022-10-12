package com.bt.marketplace.partnercredentials.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("credentials")
@Data
public class VaultConfig {
    private String encryptionKey;
}
