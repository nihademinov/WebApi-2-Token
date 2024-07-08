package org.example.webapi2.service;


import jakarta.transaction.Transactional;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.api.model.Role;
import org.example.webapi2.api.model.User;
import org.example.webapi2.repository.RoleRepository;
import org.example.webapi2.repository.UserRepesitory;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {


    private UserRepesitory userRepesitory;
    private ModelMapper modelMapper = new ModelMapper();

    public AdminService(UserRepesitory userRepesitory) {
        this.userRepesitory = userRepesitory;
    }


    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepesitory.findAll();
        return allUsers.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public List<UserDto> getUsersNotAdmin(){

        List<User> allUsersNonAdmin = userRepesitory.findAllNonAdminUsers();
        return allUsersNonAdmin.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }


    public UserDto getUsersById(Integer id)
    {
        Optional<User> resp = userRepesitory.findById(id);
        return resp.stream().map(user -> modelMapper.map(user, UserDto.class)).findFirst().orElse(null);
    }

    public void createAdmin(UserDto newUSer)
    {
        userRepesitory.save(modelMapper.map(newUSer, User.class));
    }


    public UserDto updateUser(Integer id, UserDto userDTO) {

        ModelMapper modelMapper = new ModelMapper();
        var users = userRepesitory.findById(id);

        User user = users.get();
        modelMapper.map(userDTO, user);

        User updatedUser = userRepesitory.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

//    @Transactional
    public void deleteUserById(Integer userId) {
        User user = userRepesitory.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepesitory.delete(user);
        userRepesitory.save(user);
    }




}
