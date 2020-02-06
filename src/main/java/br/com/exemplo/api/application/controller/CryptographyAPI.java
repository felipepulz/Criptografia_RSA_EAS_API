package br.com.exemplo.api.application.controller;

import br.com.exemplo.api.application.request.DecryptRequest;
import br.com.exemplo.api.application.request.EncryptRequest;
import br.com.exemplo.api.application.response.DecryptResponse;
import br.com.exemplo.api.application.response.EncryptResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api
@Validated
@RequestMapping(path = "/cryptography", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface CryptographyAPI {

    @PostMapping(path = "/decrypt")
    DecryptResponse decrypt(@Valid @RequestBody DecryptRequest request);

    @PostMapping(path = "/encrypt")
    EncryptResponse encrypt(@Valid @RequestBody EncryptRequest request);

}
	

