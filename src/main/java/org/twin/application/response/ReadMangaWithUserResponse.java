package org.twin.application.response;

import lombok.Getter;
import lombok.Setter;
import org.twin.domain.model.Manga;

@Getter
@Setter
public class ReadMangaWithUserResponse {
    private Long id;
    private String title;
    private String description;
    private int chapter;
    public Long userId;
    public ReadMangaWithUserResponse(Manga manga) {
        this.id = manga.getId();
        this.title = manga.getTitle();
        this.description = manga.getDescription();
        this.chapter = manga.getChapter();
        this.userId = manga.getUsuario().getId();
    }
}
