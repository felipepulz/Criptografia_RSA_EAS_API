package br.com.exemplo.api.domain.cryptography;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerRSA {

    KeyFactory keyFactory;
    Cipher cipher;
    Cipher cipherECB;
    KeyGenerator kgen;
    PrivateKey privateKey;
    PublicKey publicKey;
    int keySize;

    @SneakyThrows
    @Autowired
    void setSecretKey(@Value("${app.cryptography.keys.secret-key}") String secretKey,
                      @Value("${app.cryptography.keys.public-key}") String publicKey,
                      @Value("${app.cryptography.types-cryptography.rsa}") String rsa,
                      @Value("${app.cryptography.types-cryptography.eas}") String eas,
                      @Value("${app.cryptography.types-cryptography.rsa-ecb-pkcs}") String rsaEcb,
                      @Value("${app.cryptography.config.key-size}") int keySize) {

        keyFactory = KeyFactory.getInstance(rsa);
        cipher = Cipher.getInstance(rsa);
        kgen = KeyGenerator.getInstance(eas);
        cipherECB = Cipher.getInstance(rsaEcb);
        this.keySize = keySize;

        loadPrivateKey(secretKey.
                replace("-----BEGIN PRIVATE KEY-----", "").
                replace("-----END PRIVATE KEY-----", ""));

        loadPublicKey(publicKey.
                replace("-----BEGIN PUBLIC KEY-----", "").
                replace("-----END PUBLIC KEY-----", ""));

    }

    private void loadPrivateKey(String secretKey) {
        try {
            privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(secretKey)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPublicKey(String publicKey) {
        try {
            this.publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public String generateKeyEmitter() {
        SecureRandom srandom = new SecureRandom();
        byte[] iv = new byte[64 / 8];
        srandom.nextBytes(iv);
        kgen.init(this.keySize, srandom);
        cipherECB.init(Cipher.ENCRYPT_MODE, this.publicKey);
        return Base64.getEncoder().encodeToString(cipherECB.doFinal(kgen.generateKey().getEncoded()));
    }

    byte[] decrypt(byte[] keyEncrypted) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(keyEncrypted);
        } catch (InvalidKeyException e) {
            System.out.println("private key (RSA) invalid. | ".concat(e.toString()));
        } catch (Exception e) {
            System.out.println("Error when trying to decrypt with private key (RSA). | ".concat(e.toString()));
        }
        return null;
    }

}
