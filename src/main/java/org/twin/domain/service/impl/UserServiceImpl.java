package org.twin.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twin.domain.exception.UserNotFoundException;
import org.twin.domain.model.Usuario;
import org.twin.domain.service.UserService;
import org.twin.infrastructure.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Usuario createUser(Usuario user) {
        userRepository.save(user);
        return user;
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public Usuario getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }
    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }
    public Usuario updateUser(Usuario existingUser) {
        Optional<Usuario> optionalUser = userRepository.findById(existingUser.getId());
        if (optionalUser.isPresent()) {
            Usuario userToUpdate = optionalUser.get();
            userToUpdate.setUsername(existingUser.getUsername());
            userToUpdate.setPassword(existingUser.getPassword());

            return userRepository.save(userToUpdate);
        } else {
            throw new UserNotFoundException();
        }
    }
}
