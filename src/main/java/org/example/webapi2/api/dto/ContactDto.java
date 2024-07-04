package org.example.webapi2.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webapi2.api.model.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ContactDto {
    
    public String address;
}
