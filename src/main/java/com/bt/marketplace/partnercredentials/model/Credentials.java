package com.bt.marketplace.partnercredentials.model;

import com.bt.marketplace.partnercredentials.controller.common.ValidationMessage;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
public class Credentials {
    private String tenetId;
    private String userId;
    private String locationId;

    @Valid
    private OrganisationSecrets organisationSecrets;
}
