package org.example.webapi2.service;


import org.example.webapi2.api.bussines.management.AdminManager;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.api.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {


    private final AdminManager adminManager;
    private final ModelMapper modelMapper = new ModelMapper();

    public AdminService( AdminManager adminManager) {
        this.adminManager = adminManager;
    }


    public List<UserDto> getAllUsers() {
        List<User> allUsers = adminManager.getAllUsers();
        return allUsers.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }




    public UserDto getUsersById(Long id)
    {
        Optional<User> resp = adminManager.getUserById(id);
        //fixme optional check manager de handle olunmalidir servise User geri qayitmalidir, varsa tebiiki
        // User user = adminManager.getUserById(id);
        return resp.stream().map(user -> modelMapper.map(user, UserDto.class)).findFirst().orElse(null);
    }


    public UserDto updateUser(Long id, UserDto userDTO) {

        ModelMapper modelMapper = new ModelMapper();
        Optional<User> users = adminManager.getUserById(id);
        //fixme optional check managere dasinsin.


        User user = users.get();//fixme yolverilmezdir!!!
        modelMapper.map(userDTO, user);

        adminManager.saveUser(user);
        return modelMapper.map(user, UserDto.class);
    }

    public void deleteUserById(Long userId) {
        User user = adminManager.getUserById(userId)
                // fixme yanlis exception throw olunub. custom NotFoundException yaradilib o throw olunacaq managerde
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        adminManager.deleteUser(user.getId());
    }




}
