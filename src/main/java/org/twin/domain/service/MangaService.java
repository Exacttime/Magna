package org.twin.domain.service;

import org.twin.domain.model.Manga;

public interface MangaService {
    public Manga getManga(Long id);
    public Manga createManga(Manga manga);
}
