package org.example.webapi2.api.bussines.management;


import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.AuthenticationResponse;
import org.example.webapi2.api.dto.AuthenticationRequestDto;
import org.example.webapi2.api.dto.RegisterRequestDto;
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

    private UserManager userManager;
    private  final RoleManager roleManager;


    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final org.springframework.security.authentication.AuthenticationManager authenticationManager;


    public String registerUser(RegisterRequestDto request) {

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfigPassword(passwordEncoder.encode(user.getConfigPassword()));

        Role userRole = roleManager.getRoleByName("USER");
        user.setRoles(List.of(userRole));

        user.getContacts().forEach(contact -> contact.setUser(user));

        userManager.saveUser(user);

        //fixme return etmeyek, sadece ugurlu oldugu teqdirde succes response kifayetdir, login servisinden istifade edib token alsin istifadeci

        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        return "User Successfully registered";
    }



    public AuthenticationResponse authenticateUser(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userManager.userFindByEmail(request.getEmail());

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


}