package org.example.webapi2.api.controller;


import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.ResponseDto.ProductDto;
import org.example.webapi2.api.dto.ResponseDto.UserDto;
import org.example.webapi2.api.dto.RequestDto.UserRequestDto;
import org.example.webapi2.service.ProductService;
import org.example.webapi2.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductService productService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.getUsersNotAdmin();
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping()
    public String updateUser(@Valid @RequestBody UserRequestDto userDto) {
        return userService.updateUser(userDto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{productId}/products")
    public List<ProductDto> getProducts(@Valid @PathVariable Long productId) {
        return userService.getProductsByUser(productId);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
}
