package org.example.webapi2.api.controller;


import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.AuthenticationRequestDto;
import org.example.webapi2.api.dto.AuthenticationResponse;
import org.example.webapi2.api.dto.RegisterRequestDto;
import org.example.webapi2.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    //fixme yeni register olan istifadeci ozune role teyin ede bilmez,
    // register olan istifadeciler default olaraq USER role ile qeydiyyat olmalidir.

    //@ResponseStatus(HttpStatus.CREATED) yeni yaradilan servislerde 201 created statusu qaytarmaq meqsede uygundur.
    @PostMapping("/register")
    //fixme Http Response-da hec bir customizasiya etmeyeceyikse ResponseEntity yazmaq gerekmir.
    // yeni sadece:  public AuthenticationResponse register(){} kimi tertib etmek kifateydir.

    //fixme register servisinde token qaytarmayaq, sadece yeni istifadeci yaradilsin ve ugurlu olub olmadigi barede melumat vermek kifayetdir
    // daha sonra authentocate servisinden istfade ederek login olsun( email tesdiqlenmesi istiye bilerik misalcun. registerden sonra)
    public String register(@RequestBody RegisterRequestDto request) {
        return authenticationService.register(request);
    }

    //fixme default sistem istifadecileri yoxdur, istenilen adam ADMIN kimi register ede biler?
    // app ilk defe run olanda db empty olsa bele Default ADMIN role ile user yaranmalidir(standart user parol ile: admin admin)
    // diger istifadecileri de managa ede bilmek ucun
    @PostMapping("/authenticate")
    //fixme Http Response-da hec bir customizasiya etmeyeceyikse ResponseEntity yazmaq gerekmir.
    // yeni sadece:  public AuthenticationResponse register(){} kimi tertib etmek kifateydir.
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }

}
