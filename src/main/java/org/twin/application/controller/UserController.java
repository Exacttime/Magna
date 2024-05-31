package org.twin.application.controller;

import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twin.application.request.CreateUserRequest;
import org.twin.application.request.UpdateUserRequest;
import org.twin.application.response.DeleteUserResponse;
import org.twin.application.response.ReadUserResponse;
import org.twin.application.response.ReadUserWithMangaResponse;
import org.twin.application.response.UpdateUserResponse;
import org.twin.domain.exception.UserNotFoundException;
import org.twin.domain.model.Usuario;
import org.twin.domain.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<ReadUserResponse> getUserById(@PathVariable Long id) {
        Usuario user = userService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        ReadUserResponse userResponse = new ReadUserResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ReadUserWithMangaResponse>> getAllUsers() {
        List<Usuario> usuarios = userService.getAllUsers();
        List<ReadUserWithMangaResponse> userResponses = usuarios.stream()
                .map(ReadUserWithMangaResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ReadUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        Usuario user = new Usuario();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(createUserRequest.getPassword());

        Usuario createdUser = userService.createUser(user);

        ReadUserResponse userResponse = new ReadUserResponse(createdUser);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable Long userId){
        Usuario user = userService.getUserById(userId);
        userService.deleteUser(userId);
        DeleteUserResponse deleteUserResponse = new DeleteUserResponse(user, true, "O usuário foi deletado com sucesso");
        return ResponseEntity.ok(deleteUserResponse);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest) {
        Usuario existingUser = userService.getUserById(userId);
        existingUser.setUsername(updateUserRequest.getUsername());
        existingUser.setPassword(updateUserRequest.getPassword());

        Usuario updatedUser = userService.updateUser(existingUser);

        UpdateUserResponse updateUserResponse = new UpdateUserResponse(updatedUser, true, "O usuário foi atualizado com sucesso");
        return ResponseEntity.ok(updateUserResponse);
    }
}
