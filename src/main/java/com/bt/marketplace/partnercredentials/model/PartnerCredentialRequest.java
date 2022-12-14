package com.bt.marketplace.partnercredentials.model;

import com.bt.marketplace.partnercredentials.controller.common.ValidationMessage;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
public class PartnerCredentialRequest {

    @NotBlank(message = ValidationMessage.MSG_DSCR_MUST_NOT_BE_EMPTY)
    private String isvId;

    @NotBlank(message = ValidationMessage.MSG_DSCR_MUST_NOT_BE_EMPTY)
    private String customerId;

    @Valid
    private Credentials credentials;
}
