package com.hoteles.service;

import com.hoteles.dto.habitacion.HabitacionDto;
import com.hoteles.dto.habitacion.HabitacionSaveDto;

import java.util.List;

public interface IHabitacionService {
    List<HabitacionDto> findAll();
    HabitacionDto findById(Long id);
    HabitacionDto create(HabitacionSaveDto saveBody);
    HabitacionDto update(Long id, HabitacionSaveDto saveBody) ;
    HabitacionDto disable(Long id) ;
    void consumeMessage1(String message) ;
    void consumeMessage2(String message) ;

}
