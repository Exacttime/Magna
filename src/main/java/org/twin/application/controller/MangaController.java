package org.twin.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.twin.application.response.ReadMangaResponse;
import org.twin.application.response.ReadUserResponse;
import org.twin.domain.exception.UserNotFoundException;
import org.twin.domain.model.Manga;
import org.twin.domain.model.Usuario;
import org.twin.domain.service.MangaService;

@RestController
@RequestMapping("/api/mangas")
public class MangaController {
    @Autowired
    private MangaService mangaService;
    @GetMapping("/{id}")
    public ResponseEntity<ReadMangaResponse> getMangaById(@PathVariable Long id) {
        Manga manga = mangaService.getManga(id);
        if (manga == null) {
            throw new UserNotFoundException();
        }
        ReadMangaResponse userResponse = new ReadMangaResponse(manga);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
