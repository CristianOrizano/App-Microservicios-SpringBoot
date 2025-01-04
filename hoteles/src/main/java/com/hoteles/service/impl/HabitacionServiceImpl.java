package com.hoteles.service.impl;

import com.hoteles.domain.Habitacion;
import com.hoteles.dto.habitacion.HabitacionDto;
import com.hoteles.dto.habitacion.HabitacionSaveDto;
import com.hoteles.dto.habitacion.Mapper.HabitacionMapper;
import com.hoteles.repository.HabitacionRepository;
import com.hoteles.service.IHabitacionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class HabitacionServiceImpl implements IHabitacionService {

    private final HabitacionMapper habitacionMapper;
    private final HabitacionRepository habitacionRepository;

    @Override
    public List<HabitacionDto> findAll() {
        return habitacionRepository.findAll().stream()
                .map(habitacionMapper::toDto)
                .toList();
    }

    @Override
    public HabitacionDto findById(Long id) {
        return habitacionRepository.findById(id)
                .map(habitacionMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habitación no encontrada con id: " + id));
    }

    @Override
    public HabitacionDto create(HabitacionSaveDto saveBody) {
        Habitacion habitacion = habitacionMapper.toEntity(saveBody);
        return habitacionMapper.toDto(habitacionRepository.save(habitacion));
    }

    @Override
    public HabitacionDto update(Long id, HabitacionSaveDto saveBody) {
        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habitación no encontrada con id: " + id));
        habitacionMapper.updateEntity(saveBody,habitacion);
        return habitacionMapper.toDto(habitacionRepository.save(habitacion));
    }

    @Override
    public HabitacionDto disable(Long id) {
        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habitación no encontrada con id: " + id));
        habitacion.setDisponibilidad(!habitacion.getDisponibilidad());
        return habitacionMapper.toDto(habitacionRepository.save(habitacion));
    }
    @KafkaListener(topics = "str-topic", groupId = "test-group1")
    public void consumeMessage1(String message) {
        log.info("LISTENER1 ::: Recibiendo un mensaje {}",message);
    }

    @KafkaListener(topics = "str-topic", groupId = "test-group1")
    public void consumeMessage2(String message) {
        log.info("LISTENER2 ::: Recibiendo un mensaje: {}", message);
    }
}

