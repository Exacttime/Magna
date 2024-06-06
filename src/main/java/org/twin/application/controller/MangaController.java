package org.twin.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.twin.application.request.CreateMangaRequest;
import org.twin.application.request.UpdateMangaRequest;
import org.twin.application.response.DeleteMangaResponse;
import org.twin.application.response.ReadMangaResponse;
import org.twin.application.response.ReadMangaWithUserResponse;
import org.twin.domain.exception.MangaNotFoundException;
import org.twin.domain.exception.UserNotFoundException;
import org.twin.domain.model.Manga;
import org.twin.domain.model.Usuario;
import org.twin.domain.service.MangaService;
import org.twin.domain.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mangas")
public class MangaController {
    @Autowired
    private MangaService mangaService;
    @Autowired
    private UserService userService;
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ReadMangaWithUserResponse>> getAllMangas() {
        List<Manga> mangas = mangaService.getAllMangas();
        List<ReadMangaWithUserResponse> mangaWithUserResponses = mangas.stream()
                .map(ReadMangaWithUserResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(mangaWithUserResponses,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ReadMangaResponse>> getAllUserMangas(@RequestHeader Long userId) {
        List<Manga> mangas = mangaService.getAllUserMangas(userId);
        List<ReadMangaResponse> mangaWithUserResponses = mangas.stream()
                .map(ReadMangaResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(mangaWithUserResponses,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadMangaResponse> getMangaById(@PathVariable Long id) {
        Manga manga = mangaService.getManga(id);
        if (manga == null) {
            throw new MangaNotFoundException();
        }
        ReadMangaResponse userResponse = new ReadMangaResponse(manga);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    @GetMapping("/titles/{title}")
    public ResponseEntity<List<ReadMangaResponse>> getMangasByTitle(@PathVariable String title) {
        List<Manga> mangas = mangaService.getByNameContaining(title);
        List<ReadMangaResponse> mangasResponse = mangas.stream()
                .map(ReadMangaResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(mangasResponse, HttpStatus.OK);
    }
    @PostMapping(headers = "userId")
    public ResponseEntity<ReadMangaResponse> createManga(@RequestHeader("userId") Long userId, @RequestBody CreateMangaRequest createMangaRequest) {
        Manga manga = new Manga();
        manga.setTitle(createMangaRequest.getTitle());
        manga.setDescription(createMangaRequest.getDescription());
        manga.setImageUrl(createMangaRequest.getMangaUrl());
        manga.setChapter(createMangaRequest.getChapter() != null ? createMangaRequest.getChapter() : 1);

        Usuario usuario = userService.getUserById(userId);
        if (usuario == null) {
            throw new UserNotFoundException();
        }
        manga.setUsuario(usuario);

        Manga createdManga = mangaService.createManga(manga);

        ReadMangaResponse readMangaResponse = new ReadMangaResponse(createdManga);
        return new ResponseEntity<>(readMangaResponse, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ReadMangaResponse> updateManga(@PathVariable Long id, @RequestBody UpdateMangaRequest updateMangaRequest) {
        Manga manga = mangaService.getManga(id);
        manga.setTitle(updateMangaRequest.getTitle());
        manga.setDescription(updateMangaRequest.getDescription());
        manga.setChapter(updateMangaRequest.getChapter() != null ? updateMangaRequest.getChapter() : 1);

        Manga updateManga = mangaService.updateManga(manga);

        ReadMangaResponse readMangaResponse = new ReadMangaResponse(updateManga);
        return new ResponseEntity<>(readMangaResponse, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<DeleteMangaResponse> deleteManga(@RequestParam Long id) {
        Manga manga = mangaService.getManga(id);
        mangaService.deleteManga(manga.getId());
        DeleteMangaResponse deleteMangaResponse = new DeleteMangaResponse(manga,true,"Manga deletado com sucesso");
        return ResponseEntity.ok(deleteMangaResponse);
    }
}
