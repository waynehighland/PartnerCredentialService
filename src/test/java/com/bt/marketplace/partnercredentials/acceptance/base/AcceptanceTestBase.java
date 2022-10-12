package com.bt.marketplace.partnercredentials.acceptance.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AcceptanceTestBase {
    @LocalServerPort
    protected int port;

    protected String getBase() { return "http://localhost:" + port; }

    @Autowired
    protected WebTestClient webclient;
}
