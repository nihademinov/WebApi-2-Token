package org.example.webapi2.service;


import org.example.webapi2.api.bussines.management.UserManager;
import org.example.webapi2.api.dto.ResponseDto.ProductDto;
import org.example.webapi2.api.dto.ResponseDto.UserDto;
import org.example.webapi2.api.dto.RequestDto.UserRequestDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserManager userManager;
    private final ModelMapper modelMapper = new ModelMapper();

    public UserService( UserManager userManager) {
        this.userManager = userManager;
    }

    public List<UserDto> getUsersNotAdmin(){
        return userManager.findAllNonAdminUsers();
    }

    public String updateUser(UserRequestDto userRequestDto) {
        return userManager.updateUser(userRequestDto);
    }

    public List<ProductDto> getProductsByUser(Long id)
    {
        return userManager.getProductsByUser(id);
    }






}
