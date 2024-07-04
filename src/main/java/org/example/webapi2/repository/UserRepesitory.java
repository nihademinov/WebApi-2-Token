package org.example.webapi2.repository;


import org.example.webapi2.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepesitory extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);
}
