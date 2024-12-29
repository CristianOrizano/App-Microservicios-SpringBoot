package com.reservas.dto.habitacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionDto {
    private Long id;
    private Integer numeroHabitacion;
    private String tipo;
    private BigDecimal precio;
    private String descripcion;
    private Boolean disponibilidad;
    private String fechaRegistro;
}
