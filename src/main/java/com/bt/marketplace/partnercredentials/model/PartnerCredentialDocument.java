package com.bt.marketplace.partnercredentials.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Builder
@Data
public class PartnerCredentialDocument {
    @Id
    private String customerId;
    private String isvId;
    private String locationId;
    private String sensitive;
}
