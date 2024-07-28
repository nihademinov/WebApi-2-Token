package org.example.webapi2.api.bussines.management;


import org.example.webapi2.api.model.User;
import org.example.webapi2.repository.UserRepesitory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdminManager {

    private final UserRepesitory userRepesitory;

    public AdminManager(UserRepesitory userRepesitory) {
        this.userRepesitory = userRepesitory;
    }

    public List<User> getAllUsers() {
        return userRepesitory.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return  userRepesitory.findById(id);

    }

    //fixme custom NotFoundException class yaradiriq, Runtime dan extend edir
    // userManagerde findByIdlerin tekrarlanmasini qarsisini almaq ucun getUser yazib optional check i da burda edirik
    // bu diger butun entityler ucunde bele olmalidir
//    public User getUser(Long id) {
//        return userRepesitory.findById(id).orElseThrow(()-> new NotFoundException("User with id " + id + " not found"));
//    }

    public void saveUser(User user) {
        userRepesitory.save(user);
    }

    public void deleteUser(Long id) {
        userRepesitory.deleteById(id);
    }
}
