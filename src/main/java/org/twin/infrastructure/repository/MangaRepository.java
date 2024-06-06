package org.twin.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twin.domain.model.Manga;
import org.twin.domain.model.Usuario;

import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<Manga,Long> {
    List<Manga> findAllByTitleContainingIgnoreCase(String title);
    List<Manga> findAllByUsuario(Usuario usuario);

}
