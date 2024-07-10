package org.example.webapi2.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    private List<ContactDto> contacts = new ArrayList<>();


}
