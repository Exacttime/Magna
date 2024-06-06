package org.twin.domain.service;

import org.twin.domain.model.Enums.ERoles;
import org.twin.domain.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(ERoles name);

}
