package org.twin.application.response;

import lombok.Getter;
import lombok.Setter;
import org.twin.domain.model.Manga;

@Getter
@Setter
public class ReadMangaResponse {
    private String title;
    private String description;
    private String imageUrl;
    private int chapter;
    public ReadMangaResponse(Manga manga) {
        this.title = manga.getTitle();
        this.description = manga.getDescription();
        this.imageUrl = manga.getImageUrl();
        this.chapter = manga.getChapter();
    }
}
