package org.twin.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Manga não encontrado")  // 404
public class MangaNotFoundException extends RuntimeException {
}
