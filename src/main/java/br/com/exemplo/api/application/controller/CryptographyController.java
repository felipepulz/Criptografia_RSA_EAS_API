package br.com.exemplo.api.application.controller;

import br.com.exemplo.api.application.request.DecryptRequest;
import br.com.exemplo.api.application.request.EncryptRequest;
import br.com.exemplo.api.application.response.DecryptResponse;
import br.com.exemplo.api.application.response.EncryptResponse;
import br.com.exemplo.api.domain.cryptography.DecodeManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
class CryptographyController implements CryptographyAPI {

    private final DecodeManager decodeManager;

    @Autowired
    public CryptographyController(DecodeManager decodeManager) {
        this.decodeManager = decodeManager;
    }

    @Override
    public DecryptResponse decrypt(@Valid DecryptRequest request) {
        return new DecryptResponse(this.decodeManager.decryptData(request.getDataEncrypted().getBytes(), request.getKeyMaster()));
    }

    @Override
    public EncryptResponse encrypt(EncryptRequest request) {
        String keyEmissor = this.decodeManager.generateKeyEmissor();
        return new EncryptResponse(this.decodeManager.encryptData(request.getData().getBytes(), keyEmissor), keyEmissor);
    }

}
