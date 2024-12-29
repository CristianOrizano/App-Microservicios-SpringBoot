package com.hoteles.controller;

import com.hoteles.dto.habitacion.HabitacionDto;
import com.hoteles.dto.habitacion.HabitacionSaveDto;
import com.hoteles.service.IHabitacionService;
import com.hoteles.shared.constant.HttpStatusCodes;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/habitacion")
@Tag(name = "Habitacion")
public class HabitacionController {

    private final IHabitacionService habitacionService;

    @ApiResponse(responseCode = HttpStatusCodes.OK,description = "Listar Habitaciones")
    @GetMapping()
    public ResponseEntity<List<HabitacionDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(habitacionService.findAll());
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Habitacion por id")
    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(habitacionService.findById(id));
    }

    @ApiResponse(responseCode = HttpStatusCodes.CREATED, description = "Habitacion creado")
    @PostMapping
    public ResponseEntity<HabitacionDto> create(@Valid @RequestBody HabitacionSaveDto saveDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(habitacionService.create(saveDto));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Habitacion actualizado")
    @PutMapping("/{id}")
    public ResponseEntity<HabitacionDto> update(@PathVariable("id") Long id, @Valid @RequestBody HabitacionSaveDto saveDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(habitacionService.update(id, saveDto));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Habitacion Desabilitar")
    @DeleteMapping("/{id}")
    public ResponseEntity<HabitacionDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(habitacionService.disable(id));
    }


}
