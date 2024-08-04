package org.example.webapi2.api.bussines.management;

import org.example.webapi2.api.dto.ProductDto;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.api.dto.UserRequestDto;
import org.example.webapi2.api.model.Product;
import org.example.webapi2.api.model.User;
import org.example.webapi2.repository.UserRepesitory;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserManager {
    private final UserRepesitory userRepesitory;

    //fixme injection
    private final ModelMapper modelMapper = new ModelMapper();

    public UserManager(final UserRepesitory userRepesitory) {
        this.userRepesitory = userRepesitory;
    }

    public List<UserDto> findAllNonAdminUsers(){
        System.out.println("start");
        List<User> allNonAdminUsers = userRepesitory.findAllNonAdminUsers();
        System.out.println("end");
        return allNonAdminUsers.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(Long id){
        return userRepesitory.findById(id);
    }

    public void saveUser(User user){
        userRepesitory.save(user);
    }

    public void deleteUser(Long id) {
        userRepesitory.deleteById(id);
    }

    public User userFindByEmail(String email)
    {
        //fixme exception
        return userRepesitory.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public String updateUser( UserRequestDto userRequestDtoDto) {
        User user = getUser(userRequestDtoDto.getId());

        modelMapper.map(userRequestDtoDto, user);
        userRepesitory.save(user);
        return "User updated";
    }

    public User getUser(Long id) {

        return userRepesitory.findById(id)
                .orElseThrow(() -> new NotFoundExceptionManager("User not found with id: " + id));
    }

}
