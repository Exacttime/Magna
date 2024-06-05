package org.twin.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.twin.domain.model.Enums.ERoles;
import org.twin.domain.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERoles name);
}
