package com.bt.marketplace.partnercredentials.service.encryption;

public interface EncryptionService {
    String encrypt(String plainText);
    String decrypt(String cipherText);

    void rotate();
}
