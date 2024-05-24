package br.com.fiap.GestaoDeResiduos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AterroNotFoundException extends RuntimeException {
    public AterroNotFoundException(String message) {
        super(message);
    }
}
