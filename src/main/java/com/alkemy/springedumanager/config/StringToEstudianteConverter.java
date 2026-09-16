package com.alkemy.springedumanager.config;

import com.alkemy.springedumanager.model.Estudiante;
import com.alkemy.springedumanager.repository.EstudianteRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Component
public class StringToEstudianteConverter implements Converter<String, Estudiante> {

    private final EstudianteRepository estudianteRepository;

    public StringToEstudianteConverter(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public Estudiante convert(@NonNull String id) {
        if (id.isBlank()) {
            return null;
        }
        return estudianteRepository.findById(Long.valueOf(id)).orElse(null);
    }
}
