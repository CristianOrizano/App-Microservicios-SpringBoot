package com.auth.dto.usuario;

import com.auth.dto.role.RolDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private Boolean state;
    // Lista de roles del usuario, representados por un DTO
    private Set<RolDto> roles = new HashSet<>();
}
