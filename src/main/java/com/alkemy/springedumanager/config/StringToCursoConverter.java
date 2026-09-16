package com.alkemy.springedumanager.config;

import com.alkemy.springedumanager.model.Curso;
import com.alkemy.springedumanager.repository.CursoRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Component
public class StringToCursoConverter implements Converter<String, Curso> {

    private final CursoRepository cursoRepository;

    public StringToCursoConverter(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Curso convert(@NonNull String id) {
        if (id.isBlank()) {
            return null;
        }
        return cursoRepository.findById(Long.valueOf(id)).orElse(null);
    }
}
