package com.bt.marketplace.partnercredentials.acceptance.acronis.helper;

import com.bt.marketplace.partnercredentials.config.EncryptionConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AcceptanceGivenThatHelper {
    private final EncryptionConfig config;
    private final AcronisTestDataHolder testData;
}
