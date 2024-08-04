package org.example.webapi2.api.controller;


import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.AuthenticationRequestDto;
import org.example.webapi2.api.dto.AuthenticationResponse;
import org.example.webapi2.api.dto.RegisterRequestDto;
import org.example.webapi2.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDto request) {
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
