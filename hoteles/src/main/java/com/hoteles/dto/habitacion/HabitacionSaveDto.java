package com.hoteles.dto.habitacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionSaveDto {
    private Integer numeroHabitacion;
    private String tipo;
    private BigDecimal precio;
    private String descripcion;
    private Boolean disponibilidad = true;
}
