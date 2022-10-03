package com.bt.marketplace.partnercredentials.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import static java.util.Objects.requireNonNull;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SensitiveData {
    private String accessToken;
    private String refreshToken;
    private String clientId;
    private String tenetId;

    private String testId;

    @JsonCreator
    public SensitiveData(@JsonProperty("accessToken") String accessToken,
                         @JsonProperty("refreshToken") String refreshToken,
                         @JsonProperty("clientId") String clientId,
                         @JsonProperty("tenetId") String tenetId,
                         @JsonProperty("testId") String testId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.clientId = clientId;
        this.tenetId = tenetId;
        this.testId =testId;
    }
}
