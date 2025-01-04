package com.reservas.service.impl;

import com.reservas.dto.habitacion.HabitacionDto;
import com.reservas.service.IHabitacionService;
import com.reservas.shared.exeption.ResourceNotFoundException;
import com.reservas.shared.exeption.ServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j  // Lombok genera automáticamente el Logger
@Service
public class HabitacionService  implements IHabitacionService {
    private final WebClient webClient;

    public HabitacionService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("lb://HOTELES").build();
    }

    @Override
    public List<HabitacionDto> findAll() {
        return webClient.get()
                .uri("/api/habitacion")
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response -> {
                    if (response.statusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ResourceNotFoundException("No se encontraron habitaciones disponibles."));
                    }
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la solicitud: " + response.statusCode()));
                })
                .bodyToFlux(HabitacionDto.class)
                .collectList()
                .block(); // Si es síncrono
    }

    @CircuitBreaker(name = "habitacionClient", fallbackMethod = "fallbackFindById")
    @Override
    public HabitacionDto findById(Long id) {
        return webClient.get()
                .uri("/api/habitacion/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response -> {
                    if (response.statusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ResourceNotFoundException("Habitacion no encontrada con id: " + id));
                    }
                    log.error("########### entro en estatus");
                   return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la solicitud: " + response.statusCode()));
                })
                .bodyToMono(HabitacionDto.class)
                .block(); // Si es síncrono
    }

    private HabitacionDto fallbackFindById(Long id, Throwable t) {
        log.error(">>> Error al obtener habitación con ID {}: {}", id, t.getMessage());
        if (t instanceof ResourceNotFoundException) {
            throw new ResourceNotFoundException("La habitación con ID " + id + " no fue encontrada.");
        }
        // Devuelve un objeto predeterminado o lanza una excepción personalizada
        throw  new ServiceUnavailableException("El servicio de Habitacion no esta disponible. Intentelo mas tarde. " );
    }

}
