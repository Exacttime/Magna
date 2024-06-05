package org.twin.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateUserRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 4, max = 40)
    private String password;

    @NotBlank
    @Size(min = 1, max = 40)
    private String email;

    private Set<String> role;
}
