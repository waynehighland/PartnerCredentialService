package com.bt.marketplace.partnercredentials.service.encryption;

import com.bt.marketplace.partnercredentials.config.VaultConfig;
import com.bt.marketplace.partnercredentials.service.utils.AESUtil;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
public class CredentialEncryption implements EncryptionService {

    private VaultConfig vault;

    public CredentialEncryption( VaultConfig vault) {
        this.vault = vault;
    }
    public static int TAG_BIT_LENGTH = 128;

    @Override
    public String encrypt(String plainText) {
        log.info("PlainText being encrypted: " + plainText);
        byte[] iv = AESUtil.generateIv();
        byte[] aadData = vault.getAad().getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(vault.getKey()), "AES");
        GCMParameterSpec gcmParamSpec = new GCMParameterSpec(TAG_BIT_LENGTH, iv) ;

        String cipherText = null;
        if ( plainText == null ) return plainText;
        try {
            cipherText = AESUtil.encrypt(plainText, keySpec, gcmParamSpec, aadData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String encodedIv = java.util.Base64.getEncoder().encodeToString(iv);
        String encodedAAD = java.util.Base64.getEncoder().encodeToString(aadData);
        String enhancedCipherText = String.format("cred:%s:%s:%s",encodedIv,encodedAAD,cipherText);
        log.info("Cipher being stored: " + enhancedCipherText);
        return enhancedCipherText;
    }

    @Override
    public String decrypt(String cipherText) {
        log.info("Cipher being decrypted: " + cipherText);
        if ( cipherText == null ) return cipherText;

        DecryptCipher decryptCipher = new DecryptCipher(cipherText);
        SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(vault.getKey()), "AES");
        GCMParameterSpec gcmParamSpec = new GCMParameterSpec(TAG_BIT_LENGTH, decryptCipher.getInitVector());

        String plainText = null;
        try {
            byte[] base64text = Base64.getDecoder().decode(decryptCipher.getCipher());
            return AESUtil.decrypt(base64text, keySpec, gcmParamSpec, decryptCipher.getAdditionalData());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rotate() {

    }
}
