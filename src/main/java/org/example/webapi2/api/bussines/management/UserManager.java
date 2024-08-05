package org.example.webapi2.api.bussines.management;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.webapi2.ExceptionHandler.NotFoundException;
import org.example.webapi2.api.dto.ResponseDto.ProductDto;
import org.example.webapi2.api.dto.ResponseDto.UserDto;
import org.example.webapi2.api.dto.RequestDto.UserRequestDto;
import org.example.webapi2.api.model.Product;
import org.example.webapi2.api.model.User;
import org.example.webapi2.repository.UserRepesitory;
import org.modelmapper.ModelMapper;
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
        return userRepesitory.findByEmail(email).orElseThrow(() -> new NotFoundException("User doest exsist this email:"+email));
    }

    public boolean userExists(String email) {
        return userRepesitory.findByEmail(email).isPresent();
    }

    public String updateUser(UserRequestDto userRequestDtoDto) {
        User user = getUser(userRequestDtoDto.getId());

        modelMapper.map(userRequestDtoDto, user);
        userRepesitory.save(user);
        return "User updated";
    }

    public User getUser(Long id) {
        return userRepesitory.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public List<ProductDto> getProductsByUser(Long id)
    {
        User user = getUser(id);
        List<Product> products = user.getProducts();
        if(products.isEmpty())
            throw new NotFoundException("Products not found by user id: " + id);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

}
