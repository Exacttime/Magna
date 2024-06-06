package org.twin.domain.service;

import org.twin.domain.model.Manga;

import java.util.List;

public interface MangaService {
    Manga getManga(Long id);
    List<Manga> getAllUserMangas(Long id);
    Manga createManga(Manga manga);
    void deleteManga(Long id);
    List<Manga> getByNameContaining(String title);
    Manga updateManga(Manga manga);
    List<Manga> getAllMangas();
}
