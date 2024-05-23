package br.com.fiap.GestaoDeResiduos.advice;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Map<String, String> handlerIllegalArgumentException(IllegalArgumentException error) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("erro", error.getMessage());
        return map;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Map<String, String> handleMethodArgumentNotValidException (MethodArgumentNotValidException error) {
        Map<String, String> map = new HashMap<>();

        List<FieldError> fieldErrors = error.getBindingResult().getFieldErrors();

        for (FieldError fieldError: fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return map;
    }
}
