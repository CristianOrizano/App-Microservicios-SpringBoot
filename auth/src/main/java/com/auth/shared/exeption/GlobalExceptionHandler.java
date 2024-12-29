package com.auth.shared.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //exeption personalizado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetalles> ResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        int status = HttpStatus.NOT_FOUND.value(); // Código HTTP 404
        String error = HttpStatus.NOT_FOUND.getReasonPhrase(); // "Not Found"
        String message = exception.getMessage(); // Mensaje definido en la excepción
        String path = webRequest.getDescription(false).split("=")[1]; // Extrae la ruta de la solicitud

        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), status, error, message, path);
        return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);
    }
}
