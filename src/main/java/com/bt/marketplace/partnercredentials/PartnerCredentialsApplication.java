package com.bt.marketplace.partnercredentials;

import com.bt.marketplace.partnercredentials.config.VaultConfig;
import com.bt.marketplace.partnercredentials.controller.common.error.ErrorRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({VaultConfig.class, ErrorRepresentation.class})
@Slf4j
public class PartnerCredentialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartnerCredentialsApplication.class, args);
    }

}
