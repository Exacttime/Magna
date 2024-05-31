package org.twin.application.response;

import lombok.Getter;
import lombok.Setter;
import org.twin.domain.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReadUserWithMangaResponse {
    private Long id;
    private String username;
    private List<ReadMangaResponse> mangas;
    public ReadUserWithMangaResponse(Usuario usuario){
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.mangas = usuario.getMangas().stream()
                .map(ReadMangaResponse::new)
                .collect(Collectors.toList());
    }
}
