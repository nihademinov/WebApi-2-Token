package org.example.webapi2.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.service.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return adminService.getUserById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}")
    public String updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return adminService.updateUser(userId, userDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable Long userId) {
        adminService.deleteUserById(userId);
        return userId + "User is deleted ";
    }
}
