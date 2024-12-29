package com.auth.dto.usuario.mapper;

import com.auth.domain.Usuario;
import com.auth.dto.usuario.UsuarioDto;
import com.auth.dto.usuario.UsuarioSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    UsuarioDto toDto(Usuario usuario);
    @Mapping(target = "roles", ignore = true) // Ignorar roles, serán manejados en el servicio
    Usuario toEntity (UsuarioSaveDto usuarioSaveDto);
}
