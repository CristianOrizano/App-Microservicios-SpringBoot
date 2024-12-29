package com.hoteles.dto.habitacion.Mapper;

import com.hoteles.domain.Habitacion;
import com.hoteles.dto.habitacion.HabitacionDto;
import com.hoteles.dto.habitacion.HabitacionSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HabitacionMapper {

    HabitacionDto toDto(Habitacion habitacion);
    Habitacion toEntity (HabitacionSaveDto saveDto);
    Habitacion updateEntity(HabitacionSaveDto saveDto, @MappingTarget Habitacion habitacion);
}
