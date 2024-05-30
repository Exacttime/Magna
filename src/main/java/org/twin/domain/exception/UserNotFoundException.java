package org.twin.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Esse Usuario não existe")  // 404
public class UserNotFoundException extends RuntimeException{
}
