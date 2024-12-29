package com.auth.service;

import com.auth.dto.usuario.UsuarioDto;
import com.auth.dto.usuario.UsuarioSaveDto;

import java.util.List;

public interface IUsuarioService {
    List<UsuarioDto> findAll();
    UsuarioDto findById(Long id);
    UsuarioDto create(UsuarioSaveDto usuarioBody);
    UsuarioDto update(Long id, UsuarioSaveDto usuarioBody) ;
    UsuarioDto disable(Long id) ;
}
