package org.example.webapi2.service;

import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.AuthenticationRequestDto;
import org.example.webapi2.api.controller.AuthenticationResponse;
import org.example.webapi2.api.dto.RegisterRequestDto;
import org.example.webapi2.api.model.Role;
import org.example.webapi2.api.model.User;
import org.example.webapi2.repository.UserRepesitory;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepesitory userRepesitory;

    private final PasswordEncoder passwordEncoder;

    private final  JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequestDto request) {

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.getContacts().forEach(phone -> phone.setUser(user));

        userRepesitory.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequestDto request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            var user = userRepesitory.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException(request.getEmail()));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
