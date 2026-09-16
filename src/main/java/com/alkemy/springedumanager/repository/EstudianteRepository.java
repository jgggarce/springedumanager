package com.alkemy.springedumanager.repository;

import com.alkemy.springedumanager.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    Optional<Estudiante> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
}
