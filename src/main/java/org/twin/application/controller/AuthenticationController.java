package org.twin.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.twin.application.request.CreateUserRequest;
import org.twin.application.response.ErrorResponse;
import org.twin.application.response.ReadUserResponse;
import org.twin.domain.exception.UsernameAlreadyTakenException;
import org.twin.domain.model.Enums.ERoles;
import org.twin.domain.model.Role;
import org.twin.domain.model.Usuario;
import org.twin.domain.service.RoleService;
import org.twin.domain.service.UserService;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
        if (userService.existsByUsername(createUserRequest.getUsername())) {
            ErrorResponse errorResponse = new ErrorResponse("Esse Username já está sendo utilizado", HttpStatus.CONFLICT.value());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        System.out.println(createUserRequest.getUsername() + createUserRequest.getPassword());
        Usuario user = new Usuario(createUserRequest.getUsername(),passwordEncoder.encode(createUserRequest.getPassword()));
        Set<String> strRoles = createUserRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleService.findByName(ERoles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(ERoles.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleService.findByName(ERoles.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleService.findByName(ERoles.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        System.out.println(user.getUsername() + user.getPassword() + user.getRoles());
        Usuario createdUser = userService.createUser(user);

        ReadUserResponse userResponse = new ReadUserResponse(createdUser);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}