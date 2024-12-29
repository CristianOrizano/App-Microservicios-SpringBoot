package com.reservas.repository;

import com.reservas.domain.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reservacion, Long> {
}
