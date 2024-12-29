package com.auth.dto.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolDto {
    private Long id;
    private String nombre;  // O cualquier otro campo relevante de la entidad Rol
}
