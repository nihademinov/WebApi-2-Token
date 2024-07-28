package org.example.webapi2.api.controller;


import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.ProductDto;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.api.dto.UserRequestDto;
import org.example.webapi2.service.ProductService;
import org.example.webapi2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user") //fixme  /api/users
public class UserController {

    private final UserService userService;
    private  final ProductService productService;



    //fixme @RequiredArgsConstructor class seviyesinde bu annotation ile constructor create oluna biler
    //daha dinamic olacaq
    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/allUsers")
    //fixme Admin controllerde qeyd olunan naming convetionlar burada kecerlidir
    // GET /api/users list of user bize qaytarir, put, post delete de nezere al.
    public List<UserDto> getAllUsers() {
        return userService.getUsersNotAdmin();
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserRequestDto userDto) {
        return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("getProduct/{id}")
    public ResponseEntity<List<ProductDto>> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getProducts(id),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/allProducts")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }





}
