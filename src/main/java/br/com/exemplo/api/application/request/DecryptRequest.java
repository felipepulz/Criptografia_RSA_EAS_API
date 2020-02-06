package br.com.exemplo.api.application.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class DecryptRequest {

    @NotBlank(message = "dataEncrypted must be not null")
    String dataEncrypted;
    @NotBlank(message = "keyMaster must be not null")
    String keyMaster;

}
