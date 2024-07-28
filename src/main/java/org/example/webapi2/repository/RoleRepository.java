package org.example.webapi2.repository;

import org.example.webapi2.api.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //fixme niye CrudRepository?
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);

}
