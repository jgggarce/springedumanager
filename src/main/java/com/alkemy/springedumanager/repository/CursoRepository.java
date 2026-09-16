package com.alkemy.springedumanager.repository;

import com.alkemy.springedumanager.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
