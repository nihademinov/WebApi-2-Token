package org.example.webapi2.api.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.webapi2.api.model.Contact;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @NotBlank
    private String email;

    private String firstName;
    private String lastName;
    private String password;
    private String configPassword;
    private List<Contact> contacts = new ArrayList<>();
}
