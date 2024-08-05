package org.example.webapi2.repository;


import java.util.List;
import java.util.Optional;
import org.example.webapi2.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepesitory extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);


    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name <> 'ADMIN'")
    List<User> findAllNonAdminUsers();
}
