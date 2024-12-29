package com.hoteles.dto.habitacion;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class HabitacionDto {
    private Long id;
    private Integer numeroHabitacion;
    private String tipo;
    private BigDecimal precio;
    private String descripcion;
    private Boolean disponibilidad = true;
    private LocalDateTime fechaRegistro;
}
