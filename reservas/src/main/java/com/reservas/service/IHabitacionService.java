package com.reservas.service;

import com.reservas.dto.habitacion.HabitacionDto;

import java.util.List;

public interface IHabitacionService {
    List<HabitacionDto> findAll();
    HabitacionDto findById(Long id);
}
