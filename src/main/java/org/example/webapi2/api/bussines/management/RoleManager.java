package org.example.webapi2.api.bussines.management;

import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.model.Role;
import org.example.webapi2.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleManager {
    private final RoleRepository roleRepository;

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name) .orElseThrow(() -> new RuntimeException("USER role not found"));
    }



}
