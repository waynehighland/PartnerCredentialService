package com.bt.marketplace.partnercredentials.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EncryptedInfo {
    private String clientSecret;
    private String clientId;
    private String accessToken;
    private String refreshToken;
}
