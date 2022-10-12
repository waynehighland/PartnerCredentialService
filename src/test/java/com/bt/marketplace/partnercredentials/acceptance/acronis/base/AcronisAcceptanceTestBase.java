package com.bt.marketplace.partnercredentials.acceptance.acronis.base;

import com.bt.marketplace.partnercredentials.acceptance.acronis.helper.AcceptanceGivenThatHelper;
import com.bt.marketplace.partnercredentials.acceptance.acronis.helper.AcceptanceWhenHelper;
import com.bt.marketplace.partnercredentials.acceptance.base.error.helper.JsonFileReader;
import com.bt.marketplace.partnercredentials.acceptance.acronis.helper.AcronisTestDataHolder;
import com.bt.marketplace.partnercredentials.acceptance.base.AcceptanceTestBase;
import com.bt.marketplace.partnercredentials.config.EncryptionConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("acceptance")
@AutoConfigureWebTestClient(timeout = "360000")
public class AcronisAcceptanceTestBase extends AcceptanceTestBase {
    @Autowired
    protected AcceptanceGivenThatHelper givenThat;
    @Autowired
    protected AcceptanceWhenHelper when;
    @Autowired
    protected AcronisTestDataHolder testData;
    @Autowired
    protected EncryptionConfig config;
    @Autowired
    protected WebTestClient webclient;

    @BeforeEach
    public void setup() {
        when.setBase(getBase());
    }

    @Autowired
    protected JsonFileReader jsonReader;
}
