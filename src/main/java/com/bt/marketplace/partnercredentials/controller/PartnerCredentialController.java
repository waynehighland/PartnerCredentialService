package com.bt.marketplace.partnercredentials.controller;

import com.bt.marketplace.partnercredentials.model.PartnerCredentialRequest;
import com.bt.marketplace.partnercredentials.model.PartnerCredentialResponse;
import com.bt.marketplace.partnercredentials.service.PartnerCredentialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/partner")
@AllArgsConstructor
public class PartnerCredentialController {

    private final PartnerCredentialService service;

    @PostMapping("/credentials")
    @ResponseStatus(HttpStatus.CREATED)
    void postPartnerCredentialDetails(@Valid @RequestBody PartnerCredentialRequest request) {
        service.saveCredentials(request);
    }

    @GetMapping("/credentials/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    PartnerCredentialResponse getPartnerCredentialDetails(@PathVariable(required = true) String customerId) {
        return service.getCredentialDetails(customerId);
    }
}

