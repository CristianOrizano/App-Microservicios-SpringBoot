package com.auth.controller;

import com.auth.dto.usuario.UsuarioDto;
import com.auth.dto.usuario.UsuarioSaveDto;
import com.auth.service.IUsuarioService;
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
@RequestMapping("/api/usuario")
@Tag(name = "Usuario")
public class UsuarioController {
    private final IUsuarioService IUsuarioService;

    @GetMapping()
    public ResponseEntity<List<UsuarioDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IUsuarioService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IUsuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioSaveDto userCreate) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IUsuarioService.create(userCreate));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(IUsuarioService.disable(id));
    }

}
