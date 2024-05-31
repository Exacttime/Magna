package org.twin.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twin.domain.model.Manga;
import org.twin.domain.service.MangaService;
import org.twin.infrastructure.repository.MangaRepository;

import java.util.List;

@Service
public class MangaServiceImpl implements MangaService {
    @Autowired
    private MangaRepository mangaRepository;
    public MangaServiceImpl(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
    }
    public Manga createManga(Manga manga){
        mangaRepository.save(manga);
        return manga;
    }
    public void deleteManga(Long id){
        mangaRepository.deleteById(id);
    }
    public Manga getManga(Long id){
        return mangaRepository.getReferenceById(id);
    }
    public List<Manga> getAll(){
        return  mangaRepository.findAll();
    }
    public Manga updateManga(Manga manga){
        if(mangaRepository.existsById(manga.getId())){
            return mangaRepository.save(manga);
        }
        else{
            throw new RuntimeException("Manga não achado");
        }
    }
}
