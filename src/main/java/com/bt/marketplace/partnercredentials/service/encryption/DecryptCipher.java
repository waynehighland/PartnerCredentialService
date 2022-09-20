package com.bt.marketplace.partnercredentials.service.encryption;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.StringTokenizer;

@Getter
@Slf4j
public class DecryptCipher {
    private String type;
    private String cipher;
    private byte[] initVector;

    private byte[] additionalData;
    private String cipherText;

    public DecryptCipher(String ciphertext) {
        this.cipherText = ciphertext;
        unpackCipher();
    }

    private void unpackCipher() {
        StringTokenizer tokens = new StringTokenizer(this.cipherText, ":");
        int index = 0;
        while(tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            log.info("Index :" + index+ "  token:" + token);
            switch(index) {
                case 0 : this.type = token; break;
                case 1 : this.initVector = java.util.Base64.getDecoder().decode(token); break;
                case 2 : this.additionalData = java.util.Base64.getDecoder().decode(token); break;
                case 3 : this.cipher = token; break;
                default : break;
            }
            index++;
        }
    }
}
