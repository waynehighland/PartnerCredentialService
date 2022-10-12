package com.bt.marketplace.partnercredentials.model;

import com.bt.marketplace.partnercredentials.controller.common.ValidationMessage;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
public class OrganisationSecrets {
    private String clientSecret;
    private String clientId;
    private String accessToken;
    private String refreshToken;
}
