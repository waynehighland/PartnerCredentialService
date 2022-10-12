package com.bt.marketplace.partnercredentials.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class WebClientConfig {

    @Bean(name= "webClientCommon")
    public WebClient getWebClientCommon() {
        return builder().build();
    }

    @Bean
    WebClient.Builder builder() {
        return WebClient.builder();
    }

}
