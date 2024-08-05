package org.example.webapi2.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticationRequestDto {

    @NotBlank(message = "email bos ola bilmez")
    private String email;

    @NotBlank
    private String password;
}
