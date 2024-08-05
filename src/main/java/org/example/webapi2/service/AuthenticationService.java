package org.example.webapi2.service;

import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.bussines.management.AuthManager;
import org.example.webapi2.api.dto.RequestDto.AuthenticationRequestDto;
import org.example.webapi2.api.dto.ResponseDto.AuthenticationResponse;
import org.example.webapi2.api.dto.RequestDto.RegisterRequestDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthManager authManager;

    public String register(RegisterRequestDto request) {
        return authManager.registerUser(request);
    }

    public AuthenticationResponse authenticate(AuthenticationRequestDto request) {
        return authManager.authenticateUser(request);
    }
}
