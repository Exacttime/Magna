package org.twin.application.response;

import lombok.Getter;
import lombok.Setter;
import org.twin.domain.model.Usuario;
@Getter
@Setter
public class ReadUserResponse {
    private Long id;
    private String username;
    private String email;
    public ReadUserResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.email = usuario.getEmail();
    }
}
