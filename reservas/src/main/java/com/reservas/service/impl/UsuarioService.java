package com.reservas.service.impl;

import com.reservas.dto.usuario.UsuarioDto;
import com.reservas.repository.UsuarioClient;
import com.reservas.service.IUsuarioService;
import com.reservas.shared.exeption.ResourceNotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioClient usuarioClient;
    @Override
    public List<UsuarioDto> findAll() {
        return usuarioClient.findAll();
    }

    @Override
    public UsuarioDto findById(Long id) {
        try {
            return usuarioClient.findById(id);
        } catch (FeignException.NotFound e) {
            // Si el usuario no se encuentra, lanza una excepción personalizada o maneja el error
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " +id);
        }
    }
}
