package br.com.exemplo.api.domain.cryptography;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerEAS {

    Cipher cipher;
    String eas;

    @SneakyThrows
    @Autowired
    void setTypes(@Value("${app.cryptography.types-cryptography.eas}") String eas) {
        cipher = Cipher.getInstance(eas);
        this.eas = eas;
    }

    SecretKey loadKeyAES(byte[] secretKey) {
        return new SecretKeySpec(secretKey, 0, secretKey.length, eas);
    }

    byte[] encrypt(byte[] data, SecretKey secretKey) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    byte[] decrypt(byte[] encryptData, SecretKey secretKey) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(encryptData);
        } catch (Exception e) {
            return null;
        }
    }

}
