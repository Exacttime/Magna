package org.twin.application.response;

import lombok.Getter;
import lombok.Setter;
import org.twin.domain.model.Manga;

@Getter
@Setter
public class DeleteMangaResponse {
    private Long mangaId;
    private boolean success;
    private String message;

    public DeleteMangaResponse(Manga manga, boolean success, String message) {
        this.mangaId = manga.getId();
        this.success = success;
        this.message = message;
    }
}
