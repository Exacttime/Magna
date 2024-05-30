package org.twin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twin.domain.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario,Long> {
}
