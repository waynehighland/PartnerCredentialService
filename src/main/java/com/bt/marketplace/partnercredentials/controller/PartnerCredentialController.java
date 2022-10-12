package com.bt.marketplace.partnercredentials.controller;

import com.bt.marketplace.partnercredentials.model.PartnerCredentialRequest;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialResponse;
import com.bt.marketplace.partnercredentials.service.PartnerCredentialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/partner-credentials")
@AllArgsConstructor
public class PartnerCredentialController {
    private final PartnerCredentialService service;

    @PostMapping("/store")
    @ResponseStatus(HttpStatus.OK)
    void postPartnerCredentialDetails(@Valid @RequestBody PartnerCredentialRequest request) {
        service.saveCredentials(request);
    }

    @GetMapping("/store/{customerId}/{isvId}")
    @ResponseStatus(HttpStatus.OK)
    PartnerCredentialResponse getPartnerCredentialDetails(@PathVariable(required = true) String customerId,
                                                          @PathVariable(required = true) String isvId) {
        return service.getCredentialDetails(customerId,isvId);
    }
}

