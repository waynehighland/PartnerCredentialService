package com.bt.marketplace.partnercredentials.domain;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Builder
@Data
public class PartnerCredentialDocument {
    @Id
    private ObjectId id;

    // Composite key
    private String customerId;
    private String isvId;

    private String locationId;
    private String userId;
    private String tenetId;

    private String encrypted;
}
