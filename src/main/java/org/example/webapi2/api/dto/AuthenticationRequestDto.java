package org.example.webapi2.api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticationRequestDto {

    //fixme notNull notEmpty validation lar elave olunmalidir
    // @NotEmpty string parameterler ucun istifade olunur, diger opject type lar ucun NotNull istifade oluna biler
    // Controller seviyesinde requestBody nin qarsisinda @Valid annotation elave olunmalidirki validasiya islesin
    // test et.

    @NonNull
    @NotEmpty
    private String email;
    private String password;
}
