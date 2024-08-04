package org.example.webapi2.api.bussines.management;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    //fixme burda neynemek istiyirik? niye list qaytarir?
    public List<UserDto> getUserById(Long id) {

        //fixme getUser metodundan istifade ede bilerdik
        Optional<User> userOpt = userRepesitory.findById(id);
        return userOpt
                .map(user -> modelMapper.map(user, UserDto.class))
                .map(Collections::singletonList)
                .orElse(Collections.emptyList());
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

    //fixme custom NotFoundException class yaradiriq, Runtime dan extend edir
    // userManagerde findByIdlerin tekrarlanmasini qarsisini almaq ucun getUser yazib optional check i da burda edirik
    // bu diger butun entityler ucunde bele olmalidir


    public void saveUser(User user) {
        userRepesitory.save(user);
    }

    public void deleteUser(Long id) {
        userRepesitory.deleteById(id);
    }
}
