package org.example.webapi2.api.bussines.management;


import java.util.List;
import java.util.stream.Collectors;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.api.model.User;
import org.example.webapi2.repository.UserRepesitory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AdminManager {

    private final UserRepesitory userRepesitory;
    private final ModelMapper modelMapper = new ModelMapper();

    public AdminManager(UserRepesitory userRepesitory) {
        this.userRepesitory = userRepesitory;
    }

    public List<UserDto> getAllUsers() {
        return userRepesitory.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {

        return modelMapper.map(getUser(id), UserDto.class);
    }

    public String updateUser(Long id, UserDto userDto) {
        User user = getUser(id);

        modelMapper.map(userDto, user);
        userRepesitory.save(user);
        return "User updated";
    }

    public User getUser(Long id) {

        return userRepesitory.findById(id)
                .orElseThrow(() -> new NotFoundExceptionManager("User not found with id: " + id));
    }

    public void saveUser(User user) {
        userRepesitory.save(user);
    }

    public void deleteUser(Long id) {
        userRepesitory.deleteById(id);
    }
}
