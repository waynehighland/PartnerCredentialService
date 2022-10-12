package com.bt.marketplace.partnercredentials.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PartnerCredentialResponse {
    private String isvId;
    private String customerId;
    private Credentials credentials;
}
