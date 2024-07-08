package org.example.webapi2.api.controller;


import org.example.webapi2.api.dto.ProductDto;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.api.dto.UserRequestDto;
import org.example.webapi2.api.model.User;
import org.example.webapi2.service.ProductService;
import org.example.webapi2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper = new ModelMapper();
    private  final ProductService productService;



    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/allUsers")
    public List<UserDto> getAllUsers() {
        return userService.getUsersNotAdmin();
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserRequestDto userDto) {
        return new ResponseEntity<UserDto>(userService.updateUser(userDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/allProducts")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }



}
