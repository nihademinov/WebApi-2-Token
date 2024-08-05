package org.example.webapi2.api.bussines.management;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.api.dto.UserRequestDto;
import org.example.webapi2.api.model.User;
import org.example.webapi2.repository.UserRepesitory;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserManager {

    private final UserRepesitory userRepesitory;
    private final ModelMapper modelMapper;

    public List<UserDto> findAllNonAdminUsers() {
        List<User> allNonAdminUsers = userRepesitory.findAllNonAdminUsers();
        return allNonAdminUsers.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public void saveUser(User user) {
        userRepesitory.save(user);
    }

    public User userFindByEmail(String email) {
        return userRepesitory.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public String updateUser(UserRequestDto userRequestDtoDto) {
        User user = getUser(userRequestDtoDto.getId());

        modelMapper.map(userRequestDtoDto, user);
        userRepesitory.save(user);
        return "User updated";
    }

    private User getUser(Long id) {
        return userRepesitory.findById(id)
                .orElseThrow(() -> new NotFoundExceptionManager("User not found with id: " + id));
    }

}
