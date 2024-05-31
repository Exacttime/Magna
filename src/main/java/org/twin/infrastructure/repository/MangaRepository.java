package org.twin.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twin.domain.model.Manga;

@Repository
public interface MangaRepository extends JpaRepository<Manga,Long> {
}
