package com.reservas.service;

import com.reservas.dto.reservacion.ReservacionDto;
import com.reservas.dto.usuario.UsuarioDto;

import java.util.List;

public interface IUsuarioService {
    List<UsuarioDto> findAll();
    UsuarioDto findById(Long id);
}
