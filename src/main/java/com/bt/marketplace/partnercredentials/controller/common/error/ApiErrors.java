package com.bt.marketplace.partnercredentials.controller.common.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiErrors {
    private List<ErrorRepresentation> errors;
}
