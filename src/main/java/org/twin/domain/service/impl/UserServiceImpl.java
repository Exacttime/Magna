package org.twin.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twin.domain.exception.UserNotFoundException;
import org.twin.domain.model.Usuario;
import org.twin.domain.service.UserService;
import org.twin.infrastructure.repository.UserRepository;

import java.util.List;

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
    public void updateUser(Usuario user) {
        if(userRepository.existsById(user.getId())) {
            userRepository.save(user);
        }
        else{
            throw new UserNotFoundException();
        }
    }
}
