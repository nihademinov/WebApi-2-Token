package org.example.webapi2.api.bussines.management;

import org.example.webapi2.api.model.User;
import org.example.webapi2.repository.UserRepesitory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserManager {
    private final UserRepesitory userRepesitory;
    public UserManager(final UserRepesitory userRepesitory) {
        this.userRepesitory = userRepesitory;
    }

    public List<User> findAllNonAdminUsers(){
        return userRepesitory.findAllNonAdminUsers();
    }

   public Optional<User> getUserById(Long id){
        return userRepesitory.findById(id);
   }

   public void saveUser(User user){
        userRepesitory.save(user);
   }
}
