package com.reservas.shared.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //exception personalizado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetalles> ResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        int status = HttpStatus.NOT_FOUND.value(); // Código HTTP 404
        String error = HttpStatus.NOT_FOUND.getReasonPhrase(); // "Not Found"
        String message = exception.getMessage(); // Mensaje definido en la excepción
        String path = webRequest.getDescription(false).split("=")[1]; // Extrae la ruta de la solicitud

        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), status, error, message, path);
        return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ErrorDetalles> handleServiceUnavailableException(ServiceUnavailableException exception, WebRequest webRequest) {
        int status = HttpStatus.SERVICE_UNAVAILABLE.value(); // Código HTTP 404
        String error = HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(); // "Not Found"
        String message = exception.getMessage(); // Mensaje definido en la excepción
        String path = webRequest.getDescription(false).split("=")[1]; // Extrae la ruta de la solicitud

        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), status, error, message, path);
        return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);
    }
    // Maneja la excepción ResponseStatusException (503)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDetalles> handleServiceUnavailableException(ResponseStatusException exception, WebRequest webRequest) {
        int status = HttpStatus.SERVICE_UNAVAILABLE.value(); // Código HTTP 503
        String error = HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(); // "Service Unavailable"
        String message = exception.getMessage(); // Mensaje de la excepción
        String path = webRequest.getDescription(false).split("=")[1]; // Ruta de la solicitud

        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), status, error, message, path);
        return new ResponseEntity<>(errorDetalles, HttpStatus.SERVICE_UNAVAILABLE);
    }

    // para manejar cualquier excepción general en tu aplicación.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalles> manejarGlobalException(Exception exception, WebRequest webRequest){
        System.out.println("entro aqui exception global ===>");
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String error = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        String message = exception.getMessage();
        String path = webRequest.getDescription(false).split("=")[1];

        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), status, error, message, path);
        return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
