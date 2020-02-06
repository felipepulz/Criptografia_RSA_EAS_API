package br.com.exemplo.api.domain.cryptography;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DecodeManager {

    ManagerEAS managerEAS;
    ManagerRSA managerRSA;

    @Autowired
    DecodeManager(ManagerEAS managerEAS, ManagerRSA managerRSA) {
        this.managerEAS = managerEAS;
        this.managerRSA = managerRSA;
    }

    public String encryptData(byte[] data, String keyEmissor) {
        return Base64.getEncoder().encodeToString(managerEAS.encrypt(data, getSecretKey(keyEmissor)));
    }

    public String decryptData(byte[] data, String keyEmissor) {
        return new String(managerEAS.decrypt(Base64.getDecoder().decode(data), getSecretKey(keyEmissor)), UTF_8);
    }

    private SecretKey getSecretKey(String keyEmissor) {
        return managerEAS.loadKeyAES(managerRSA.decrypt(Base64.getDecoder().decode(keyEmissor)));
    }

    public String generateKeyEmissor(){
        return managerRSA.generateKeyEmitter();
    }

}
