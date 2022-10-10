package com.bt.marketplace.partnercredentials.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PartnerCredentialRequest {
    private String isvId;
    private String locationId;
    private String tenetId;
    private String accessToken;
    private String refreshToken;
    private String customerId;
    private String clientId;
}
