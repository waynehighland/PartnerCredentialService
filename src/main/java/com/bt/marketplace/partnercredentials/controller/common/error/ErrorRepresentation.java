package com.bt.marketplace.partnercredentials.controller.common.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "errorcodes")
public class ErrorRepresentation {
    private String code, message, description;

    public ErrorRepresentation(ErrorRepresentation errorRepresentation) {
        this.code = errorRepresentation.getCode();
        this.message = errorRepresentation.getMessage();
        this.description = errorRepresentation.getDescription();
    }
}
