package br.com.exemplo.api.application.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class EncryptResponse {

    @NotBlank(message = "dataEncrypted must be not null")
    String dataEncrypted;
    @NotBlank(message = "keyMaster must be not null")
    String keyMaster;

}
