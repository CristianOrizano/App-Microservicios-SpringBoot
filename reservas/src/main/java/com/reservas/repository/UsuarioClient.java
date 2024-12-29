package com.reservas.repository;

import com.reservas.dto.usuario.UsuarioDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

//@FeignClient(name = "auth-service",url = "http://localhost:8091")
@FeignClient(name = "AUTH")
public interface UsuarioClient {
    @GetMapping("/api/usuario/{id}")
    @CircuitBreaker(name = "usuarioClient", fallbackMethod = "fallbackFindById")
    UsuarioDto findById(@PathVariable("id") Long id);
    @GetMapping("/api/usuario")
    @CircuitBreaker(name = "usuarioClient", fallbackMethod = "fallbackFindAll")
    List<UsuarioDto> findAll();

    default UsuarioDto fallbackFindById(Long id, Throwable t) {
        throw  new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "El servicio de usuario no está disponible. Inténtelo más tarde. " );
    }
    default List<UsuarioDto> fallbackFindAll(Throwable t) {
        // Manejo del fallback
        return Collections.emptyList(); // o cualquier lógica de fallback
    }
}
