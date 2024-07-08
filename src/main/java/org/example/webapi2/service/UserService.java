package org.example.webapi2.service;


import org.example.webapi2.api.dto.ProductDto;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.api.dto.UserRequestDto;
import org.example.webapi2.api.model.Product;
import org.example.webapi2.api.model.User;
import org.example.webapi2.repository.UserRepesitory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {


    private UserRepesitory userRepesitory;
    private ModelMapper modelMapper = new ModelMapper();

    public UserService(UserRepesitory userRepesitory) {
        this.userRepesitory = userRepesitory;
    }

    public List<UserDto> getUsersNotAdmin(){

        List<User> allUsersNonAdmin = userRepesitory.findAllNonAdminUsers();
        return allUsersNonAdmin.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto updateUser(UserRequestDto userRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        var users = userRepesitory.findById(userRequestDto.getId());

        User user = users.get();
        modelMapper.map(userRequestDto, user);

        User updatedUser = userRepesitory.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    public List<ProductDto> getProducts(int id)
    {
        Optional<User> users = userRepesitory.findById(id);
        User user = users.get();
        List<Product> products = user.getProducts();
        if(products.isEmpty())
            return null;
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }




}
