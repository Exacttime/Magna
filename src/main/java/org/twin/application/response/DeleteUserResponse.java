package org.twin.application.response;

import lombok.Getter;
import lombok.Setter;
import org.twin.domain.model.Usuario;

@Getter
@Setter
public class DeleteUserResponse {
    private Long userId;
    private boolean success;
    private String message;

    public DeleteUserResponse(Usuario user, boolean success, String message) {
        this.userId = user.getId();
        this.success = success;
        this.message = message;
    }
}
