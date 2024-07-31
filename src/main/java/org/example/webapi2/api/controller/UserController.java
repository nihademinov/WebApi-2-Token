package org.example.webapi2.api.controller;


import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.ProductDto;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.api.dto.UserRequestDto;
import org.example.webapi2.service.ProductService;
import org.example.webapi2.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") //fixme /api/users
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductService productService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    // GET /api/users list of user bize qaytarir, put, post delete de nezere al.
    public List<UserDto> getAllUsers() {
        return userService.getUsersNotAdmin();
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping()
    public String updateUser(@RequestBody UserRequestDto userDto) {
        return userService.updateUser(userDto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{productId}/products")
    public List<ProductDto> getProducts(@PathVariable Long productId) {
        return userService.getProducts(productId);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
}
