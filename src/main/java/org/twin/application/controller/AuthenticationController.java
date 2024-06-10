package org.twin.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.twin.application.request.CreateUserRequest;
import org.twin.application.request.UserLoginRequest;
import org.twin.application.response.ErrorResponse;
import org.twin.application.response.ReadUserResponse;
import org.twin.application.response.UserLoginResponse;
import org.twin.domain.model.Enums.ERoles;
import org.twin.domain.model.Role;
import org.twin.domain.model.Usuario;
import org.twin.domain.service.RoleService;
import org.twin.domain.service.UserService;
import org.twin.infrastructure.security.JwtUtils;

import java.util.HashSet;
import java.util.List;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
        if (userService.existsByUsername(createUserRequest.getUsername())) {
            ErrorResponse errorResponse = new ErrorResponse("Esse Username já está sendo utilizado", HttpStatus.CONFLICT.value());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        System.out.println(createUserRequest.getUsername() + createUserRequest.getPassword());
        Usuario user = new Usuario(createUserRequest.getUsername(),passwordEncoder.encode(createUserRequest.getPassword()), createUserRequest.getEmail());
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
        //System.out.println(user.getUsername() + user.getPassword() + user.getRoles());
        //TODO remover o comentario que estava sendo usado para testar.
        Usuario createdUser = userService.createUser(user);

        ReadUserResponse userResponse = new ReadUserResponse(createdUser);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(),userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        List<String> roles = usuario.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return ResponseEntity.ok(new UserLoginResponse(jwt,
                usuario.getId(),
                usuario.getUsername(),
                roles));
    }
}
