package org.twin.domain.service;

import org.twin.domain.model.Usuario;

public interface UserService {
    Usuario getUserById(Long id);
    Usuario createUser(Usuario user);
}
