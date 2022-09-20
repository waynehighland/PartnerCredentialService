package com.bt.marketplace.partnercredentials;

import com.bt.marketplace.partnercredentials.config.VaultConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(VaultConfig.class)
@Slf4j
public class PartnerCredentialsApplication implements CommandLineRunner {

    @Autowired
    private VaultConfig vault;

    public static void main(String[] args) {
        SpringApplication.run(PartnerCredentialsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Environment key: " + vault.getKey());
    }
}
