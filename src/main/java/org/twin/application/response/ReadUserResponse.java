package org.twin.application.response;

import org.twin.domain.model.Usuario;

public class ReadUserResponse {
    private Long id;
    private String username;
    private List<MangaResponse> mangas;

    // Construtor que mapeia de Usuario para ReadUserResponse
    public ReadUserResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        // Mapeia a lista de mangas
        this.mangas = usuario.getMangas().stream()
                .map(MangaResponse::new)
                .toList();
    }
}
