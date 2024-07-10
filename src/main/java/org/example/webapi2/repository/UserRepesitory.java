package org.example.webapi2.repository;


import org.example.webapi2.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepesitory extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name <> 'ROLE_ADMIN'")
    List<User> findAllNonAdminUsers();
}
