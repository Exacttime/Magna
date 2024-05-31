package org.twin.domain.service;

import org.twin.domain.model.Manga;

import java.util.List;

public interface MangaService {
    public Manga getManga(Long id);
    public Manga createManga(Manga manga);
    public List<Manga> getByNameContaining(String title);
}
