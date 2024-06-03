package org.twin.domain.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.twin.domain.model.Usuario;

import java.util.List;

public interface UserService extends UserDetailsService {
    Usuario getUserById(Long id);
    Usuario createUser(Usuario user);
    List<Usuario> getAllUsers();
    void deleteUser(Long id);
    Usuario updateUser(Usuario existingUser);
    boolean existsByUsername(String username);
}
