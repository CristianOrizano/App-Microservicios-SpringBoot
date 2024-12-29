package com.auth.service.impl;

import com.auth.domain.Rol;
import com.auth.domain.Usuario;
import com.auth.dto.usuario.UsuarioDto;
import com.auth.dto.usuario.UsuarioSaveDto;
import com.auth.dto.usuario.mapper.UsuarioMapper;
import com.auth.repository.RolRepository;
import com.auth.repository.UsuarioRepository;
import com.auth.service.IUsuarioService;
import com.auth.shared.exeption.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDto)
                .toList();
    }

    @Override
    public UsuarioDto findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDto)
                .orElseThrow(() ->  new ResourceNotFoundException("Usuario no encontrada con ID: " + id));
    }

    @Override
    public UsuarioDto create(UsuarioSaveDto usuarioBody) {
        Usuario usuario = usuarioMapper.toEntity(usuarioBody);
        // Lógica avanzada: Obtener roles desde la base de datos
        Set<Rol> roles = usuarioBody.getRoles().stream()
                .map(id -> rolRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con id: " + id)))
                .collect(Collectors.toSet());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setState(true);
        usuario.setRoles(roles);
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDto update(Long id, UsuarioSaveDto usuarioBody) {
        return null;
    }

    @Override
    public UsuarioDto disable(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Usuario no encontrada con ID: " + id));
        usuario.setState(!usuario.getState());
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }
}