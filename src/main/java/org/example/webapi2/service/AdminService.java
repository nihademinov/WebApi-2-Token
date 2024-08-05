package org.example.webapi2.service;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.bussines.management.AdminManager;
import org.example.webapi2.api.bussines.management.UserManager;
import org.example.webapi2.api.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {


    private final AdminManager adminManager;

    public List<UserDto> getAllUsers() {
        return adminManager.getAllUsers();
    }

    public UserDto getUserById(Long id) {
        return adminManager.getUserById(id);
    }

    public String updateUser(Long id, UserDto userDTO) {

        return adminManager.updateUser(id, userDTO);
    }

    public void deleteUserById(Long userId) {
        adminManager.deleteUser(userId);
    }


}
