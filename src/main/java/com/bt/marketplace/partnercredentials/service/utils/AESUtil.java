package com.bt.marketplace.partnercredentials.service.utils;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AESUtil {
    private static String AES_GCM_ALGO = "AES/GCM/NoPadding";
    public static int IV_SIZE = 12;
    public static int AES_KEY_SIZE = 256;

    public static SecretKey generateKey() {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyGenerator.init(AES_KEY_SIZE);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 2145, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secret;
    }

    public static byte[] generateIv() {
        byte[] iv = new byte[IV_SIZE];
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    public static String encrypt(String input, SecretKey key,
                                 GCMParameterSpec gcmParamSpec, byte[] aadData) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(AES_GCM_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmParamSpec, new SecureRandom());
        cipher.updateAAD(aadData);

        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }
    public static String decrypt(byte[] cipherText, SecretKey aesKey,
                                 GCMParameterSpec gcmParamSpec, byte[] aadData) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(AES_GCM_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, aesKey, gcmParamSpec, new SecureRandom());
        cipher.updateAAD(aadData);

        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText);
    }
}
