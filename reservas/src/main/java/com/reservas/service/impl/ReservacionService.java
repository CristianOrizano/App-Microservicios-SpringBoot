package com.reservas.service.impl;

import com.reservas.domain.Reservacion;
import com.reservas.dto.reservacion.Mapper.ReservacionMapper;
import com.reservas.dto.reservacion.ReservacionDto;
import com.reservas.dto.reservacion.ReservacionSaveDto;
import com.reservas.repository.ReservaRepository;
import com.reservas.service.IHabitacionService;
import com.reservas.service.IReservacionService;
import com.reservas.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservacionService implements IReservacionService {

    private final ReservacionMapper reservacionMapper;
    private final ReservaRepository reservacionRepository;
    private final IHabitacionService habitacionService;
    private final IUsuarioService usuarioService;
    //kafka
    private final KafkaTemplate<String,String> kafkaTemplate;
    private static final String TOPIC = "str-topic";
    @Override
    public List<ReservacionDto> findAll() {
        return reservacionRepository.findAll().stream()
                .map(reservacionMapper::toDto)
                .toList();
    }

    @Override
    public ReservacionDto findById(Long id) {
        return reservacionRepository.findById(id)
                .map(reservacionMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservacion no encontrada con id: " + id));
    }

    @Override
    public ReservacionDto create(ReservacionSaveDto saveBody) {
        usuarioService.findById(saveBody.getUsuarioId());
        habitacionService.findById(saveBody.getHabitacionId());
        Reservacion reservacion = reservacionMapper.toEntity(saveBody);
        reservacion.setState(true);
        return reservacionMapper.toDto(reservacionRepository.save(reservacion));
    }

    @Override
    public ReservacionDto update(Long id, ReservacionSaveDto saveBody) {
        Reservacion reservacion = reservacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservacion no encontrada con id: " + id));
        reservacionMapper.updateEntity(saveBody,reservacion);
        return reservacionMapper.toDto(reservacionRepository.save(reservacion));
    }

    @Override
    public ReservacionDto disable(Long id) {
        Reservacion reservacion = reservacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservacion no encontrada con id: " + id));
        reservacion.setState(!reservacion.getState());
        return reservacionMapper.toDto(reservacionRepository.save(reservacion));
    }

    public void sendMessage(String message){
        kafkaTemplate.send(TOPIC,message).whenComplete((result,ex) -> {
            if(ex != null){
                log.error(">>>> Error, al enviar el mensaje: {}",ex.getMessage());
            }
            log.info(">>>> Mensaje enviado con éxito: {}",result.getProducerRecord().value());
            log.info(">>>> Particion {}, Offset {}", result.getRecordMetadata().partition(),result.getRecordMetadata().offset());
        });
    }

}
