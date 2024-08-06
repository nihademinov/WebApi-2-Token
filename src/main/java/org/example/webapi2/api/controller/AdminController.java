package org.example.webapi2.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.ResponseDto.UserDto;
import org.example.webapi2.service.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/{userId}")
    public UserDto getUserById(@Valid @PathVariable Long userId) {
        return adminService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public String updateUser(@Valid @PathVariable Long userId, @RequestBody UserDto userDto) {
        return adminService.updateUser(userId, userDto);
    }

    @DeleteMapping("{userId}")
    public String deleteUser(@Valid @PathVariable Long userId) {
        adminService.deleteUserById(userId);
        return userId + "User is deleted ";
    }

    @GetMapping()
    public List<UserDto> getAllUsers() {
        return adminService.getAllUsers();
    }
}
