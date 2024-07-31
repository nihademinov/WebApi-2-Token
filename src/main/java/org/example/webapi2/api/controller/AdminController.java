package org.example.webapi2.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    //fixme yazilan servisin sirf authorization ferqi yaratmaq ucun dizayn olundugunu nezere alaraq
    // adlandirma ve servis bolgusunu gormezden gele bilerik ancaq asagidaki commentleri nezerden kecirmek lazimdir

    //fixme servisler ancaq userlerin management ile baglidir, bu UserController olmalidir
    // ancaq role check servislerin uzerinde ferqlene biler,
    // eyni servis     @PreAuthorize("hasAnyRole('ADMIN','USER')") bu formada authorize ola biler

    private final AdminService adminService;

    //fixme constructoru annotation ile create ede bilerik, daha dinamic ve clean olacaq

    //fixme endpoint bu cur adlandirilmamalidir,
    // User servisinde @RequestMapping deye naming bu cur olmalidir
    // api/users
    // servislerde ise @GetMapping de user listi gelecekse elave endpoint qeyd olunmur yeni:
    // api/users endpointi Users listini qaytarmalidir, api/users/allUsers -e ehtiyac yoxdur
    // specific user ise /api/users/{userId} olaraq dizayn oluna biler


    @PreAuthorize("hasRole('ADMIN')")
    // specific user ise /api/users/{userId} olaraq dizayn oluna biler
    @GetMapping("/{userId}")
    //fixme ResponseEntity ye ehtiyac yoxdur
    public UserDto getUserById(@PathVariable Long userId) {
        return adminService.getUserById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}")
    //fixme http method tipi duzgun secildiyi muddetce /updateUser/{id} adlandirmasina ehtiyac yoxdur
    // @PutMapping("/{userId}") kifayetdir -> /api/users/{userId} PUT
    public String updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return adminService.updateUser(userId, userDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //fixme http method tipi duzgun secildiyi muddetce /deleteUser/{id} adlandirmasina ehtiyac yoxdur
    // @DeleteMapping("/{userId}") kifayetdir -> /api/users/{userId} DEL
    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable Long userId) {
        adminService.deleteUserById(userId);
        return userId + "User is deleted ";
    }

    //fixme ProductController yaradib /api/products kimi adlandirmaq lazimdir
    //Product Crud operations.

    //fixme Category servisi yaradilib /api/categories kimi adlandirilsin,
    // yuxarida qeyd edilen naming convention lar nezere alinsin

    //Category Crud operations.
}
