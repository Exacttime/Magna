package org.twin.domain.service;

import org.twin.domain.model.Usuario;

import java.util.List;

public interface UserService {
    Usuario getUserById(Long id);
    Usuario createUser(Usuario user);
    List<Usuario> getAllUsers();
    void deleteUser(Long id);
    Usuario updateUser(Usuario existingUser);
}
