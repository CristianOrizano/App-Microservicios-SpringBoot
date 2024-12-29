package com.hoteles.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Setter
@Getter
@Entity
@Table(name = "tb_habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_habitacion", nullable = false)
    private Integer numeroHabitacion;
    private String tipo;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;
    private String descripcion;
    private Boolean disponibilidad = true;
    @Column(name = "fecha_registro", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;

}
