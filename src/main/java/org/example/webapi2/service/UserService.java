package org.example.webapi2.service;


import org.example.webapi2.api.bussines.management.UserManager;
import org.example.webapi2.api.dto.ProductDto;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.api.dto.UserRequestDto;
import org.example.webapi2.api.model.Product;
import org.example.webapi2.api.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {


    private final UserManager userManager;
    private final ModelMapper modelMapper = new ModelMapper();

    public UserService( UserManager userManager) {
        this.userManager = userManager;
    }

    public List<UserDto> getUsersNotAdmin(){

        List<User> allUsersNonAdmin = userManager.findAllNonAdminUsers();
        return allUsersNonAdmin.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto updateUser(UserRequestDto userRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        var users = userManager.getUserById(userRequestDto.getId());

        User user = users.get();
        modelMapper.map(userRequestDto, user);

        userManager.saveUser(user);
        return modelMapper.map(user, UserDto.class);
    }

    public List<ProductDto> getProducts(Long id)
    {
        Optional<User> users = userManager.getUserById(id);
        User user = users.get();
        List<Product> products = user.getProducts();
        if(products.isEmpty())
            return null;
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }




}
