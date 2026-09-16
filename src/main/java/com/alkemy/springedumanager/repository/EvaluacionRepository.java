package com.alkemy.springedumanager.repository;

import com.alkemy.springedumanager.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    List<Evaluacion> findByEstudianteId(Long estudianteId);

    List<Evaluacion> findByCursoId(Long cursoId);
}
