package com.reservas.controller;

import com.reservas.dto.habitacion.HabitacionDto;
import com.reservas.dto.reservacion.ReservacionDto;
import com.reservas.dto.reservacion.ReservacionSaveDto;
import com.reservas.dto.usuario.UsuarioDto;
import com.reservas.service.IHabitacionService;
import com.reservas.service.IReservacionService;
import com.reservas.service.IUsuarioService;
import com.reservas.shared.constant.HttpStatusCodes;
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
@RequestMapping("/api/reservacion")
@Tag(name = "Reservacion")
public class ReservacionController {

    private final IReservacionService reservacionService;
    private final IUsuarioService usuarioService;
    private final IHabitacionService habitacionService;

    @ApiResponse(responseCode = HttpStatusCodes.OK,description = "Listar Reservacion")
    @GetMapping()
    public ResponseEntity<List<ReservacionDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservacionService.findAll());
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK,description = "Listar Usuarios")
    @GetMapping("usuarios")
    public ResponseEntity<List<UsuarioDto>> findAllUsuarios() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.findAll());
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK,description = "Listar Habitaciones")
    @GetMapping("habitaciones")
    public ResponseEntity<List<HabitacionDto>> findAllHabitaciones() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(habitacionService.findAll());
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Reservacion por id")
    @GetMapping("/{id}")
    public ResponseEntity<ReservacionDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservacionService.findById(id));
    }

    @ApiResponse(responseCode = HttpStatusCodes.CREATED, description = "Reservacion creado")
    @PostMapping
    public ResponseEntity<ReservacionDto> create(@Valid @RequestBody ReservacionSaveDto saveDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservacionService.create(saveDto));
    }

    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        reservacionService.sendMessage(message);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Mensaje enviado: "+ message);
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK, description = "Habitacion actualizado")
    @PutMapping("/{id}")
    public ResponseEntity<ReservacionDto> update(@PathVariable("id") Long id, @Valid @RequestBody ReservacionSaveDto saveDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservacionService.update(id, saveDto));
    }

    @ApiResponse(responseCode = HttpStatusCodes.OK ,description = "Reservacion Desabilitar")
    @DeleteMapping("/{id}")
    public ResponseEntity<ReservacionDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservacionService.disable(id));
    }

}
