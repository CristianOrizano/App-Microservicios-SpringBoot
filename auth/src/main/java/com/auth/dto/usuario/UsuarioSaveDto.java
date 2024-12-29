package com.auth.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSaveDto {
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private Set<Long> roles; // Lista de IDs de roles que se asignarán al usuario

}