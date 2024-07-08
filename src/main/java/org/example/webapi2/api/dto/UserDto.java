package org.example.webapi2.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.webapi2.api.model.Contact;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class UserDto {

        private String email;
        private String firstName;
        private String lastName;

        private List<ContactDto> contacts = new ArrayList<>();


    }
