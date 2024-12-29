package com.reservas.dto.reservacion.Mapper;

import com.reservas.domain.Reservacion;
import com.reservas.dto.reservacion.ReservacionDto;
import com.reservas.dto.reservacion.ReservacionSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservacionMapper {

    ReservacionDto toDto(Reservacion reservacion);
    Reservacion toEntity (ReservacionSaveDto saveDto);
    Reservacion updateEntity(ReservacionSaveDto saveDto, @MappingTarget Reservacion reservacion);

}
