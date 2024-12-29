package com.auth.repository;

import com.auth.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository  extends JpaRepository<Rol, Long> {
}

