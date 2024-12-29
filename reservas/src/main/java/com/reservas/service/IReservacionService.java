package com.reservas.service;

import com.reservas.dto.reservacion.ReservacionDto;
import com.reservas.dto.reservacion.ReservacionSaveDto;

import java.util.List;

public interface IReservacionService {
    List<ReservacionDto> findAll();
    ReservacionDto findById(Long id);
    ReservacionDto create(ReservacionSaveDto saveBody);
    ReservacionDto update(Long id, ReservacionSaveDto saveBody) ;
    ReservacionDto disable(Long id) ;
}
