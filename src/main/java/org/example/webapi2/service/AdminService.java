package org.example.webapi2.service;


import org.example.webapi2.api.bussines.management.AdminManager;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.api.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {


    private final AdminManager adminManager;

    public AdminService(AdminManager adminManager) {
        this.adminManager = adminManager;
    }


    public List<UserDto> getAllUsers() {
        return adminManager.getAllUsers();
    }

    public UserDto getUserById(Long id) {
        //fixme optional check manager de handle olunmalidir servise User geri qayitmalidir, varsa tebiiki
        // User user = adminManager.getUserById(id);
        return adminManager.getUserById(id).stream().findFirst().orElse(null);
    }

    public String updateUser(Long id, UserDto userDTO) {

        return adminManager.updateUser(id, userDTO);
    }

    public void deleteUserById(Long userId) {
        User user = adminManager.getUser(userId);
        // fixme yanlis exception throw olunub. custom NotFoundException yaradilib o throw olunacaq managerde
        adminManager.deleteUser(user.getUserAccountId());
    }


}
