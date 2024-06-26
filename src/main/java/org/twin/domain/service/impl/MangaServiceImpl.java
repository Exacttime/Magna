package org.twin.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twin.domain.exception.MangaNotFoundException;
import org.twin.domain.model.Manga;
import org.twin.domain.model.Usuario;
import org.twin.domain.service.MangaService;
import org.twin.domain.service.UserService;
import org.twin.infrastructure.repository.MangaRepository;

import java.util.List;

@Service
public class MangaServiceImpl implements MangaService {
    @Autowired
    private MangaRepository mangaRepository;
    @Autowired
    private UserService userService;
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
    public Manga getManga(Long id) {
        return mangaRepository.findById(id)
                .orElseThrow(MangaNotFoundException::new);
    }

    @Override
    public List<Manga> getAllUserMangas(Long id) {
        Usuario actualUser = userService.getUserById(id);
        return mangaRepository.findAllByUsuario(actualUser);
    }

    public List<Manga> getAllMangas(){
        return mangaRepository.findAll();
    }
    public List<Manga> getByNameContaining(String title){
        return mangaRepository.findAllByTitleContainingIgnoreCase(title);
    }
    public Manga updateManga(Manga manga){
        if(mangaRepository.existsById(manga.getId())){
            return mangaRepository.save(manga);
        }
        else{
            throw new MangaNotFoundException();
        }
    }
}
