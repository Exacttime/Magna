package org.twin.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twin.domain.model.Enums.ERoles;
import org.twin.domain.model.Role;
import org.twin.domain.service.RoleService;
import org.twin.infrastructure.repository.RoleRepository;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(ERoles name) {
        return roleRepository.findByName(name);
    }
}
