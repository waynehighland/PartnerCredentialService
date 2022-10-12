package com.bt.marketplace.partnercredentials.controller.common.error;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "errorcodes")
@Getter
@Slf4j
public class ApplicationErrorConfigServiceImpl {

    private Map<String, ErrorRepresentation> errors;

    public ApplicationErrorConfigServiceImpl( Map<String, ErrorRepresentation errors) {
        this.errors = errors;
    }

    public ErrorRepresentation getErrorRepresentationByCode(String errorCode) {
        return new ErrorRepresentation(getRepresentationbyCodeOrDefaultError(errorCode));
    }

    private ErrorRepresentation getRepresentationbyCodeOrDefaultError(String errorCode) {
        ErrorRepresentation errorRepresentation;
        if (StringUtils.isBlank(errorCode) || !errors.containsKey(errorCode)) {
            errorRepresentation = getInternalErrorRepresentation();
        } else {
            errorRepresentation = this.errors.get(errorCode);
            if (errorRepresentation == null) {
                logError(log, "can not find error code from list of predefined errors, returning default error");
                errorRepresentation = getInternalErrorRepresentation();
            }
        }
        return errorRepresentation;
    }

    private ErrorRepresentation getInternalErrorRepresentation() {
        ErrorRepresentation errorRepresentation;
        errorRepresentation = ErrorRepresentation.builder()
                .code(StandardError.INTERNAL_ERROR.getCode())
                .message(StandardError.INTERNAL_ERROR.getMessage()).build()
                .build();
    }
}
