package com.alkemy.springedumanager.config;

import com.alkemy.springedumanager.model.Curso;
import com.alkemy.springedumanager.model.Estudiante;
import com.alkemy.springedumanager.model.Evaluacion;
import com.alkemy.springedumanager.model.TipoEvaluacion;
import com.alkemy.springedumanager.repository.CursoRepository;
import com.alkemy.springedumanager.repository.EstudianteRepository;
import com.alkemy.springedumanager.repository.EvaluacionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Carga datos de ejemplo la primera vez que arranca la aplicacion, para que
 * se pueda probar el flujo completo (estudiantes, cursos, evaluaciones) sin
 * tener que cargar nada a mano. Si ya existen datos, no hace nada.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;
    private final EvaluacionRepository evaluacionRepository;

    public DataSeeder(EstudianteRepository estudianteRepository,
                       CursoRepository cursoRepository,
                       EvaluacionRepository evaluacionRepository) {
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
        this.evaluacionRepository = evaluacionRepository;
    }

    @Override
    public void run(String... args) {
        if (cursoRepository.count() > 0) {
            return;
        }

        Curso java = cursoRepository.save(new Curso("Java Backend", "Fundamentos de Java y POO", 80));
        Curso spring = cursoRepository.save(new Curso("Spring Framework", "Spring Boot, MVC, JPA y Security", 60));
        Curso frontend = cursoRepository.save(new Curso("Frontend con React", "SPA con React y consumo de APIs REST", 60));

        Estudiante javiera = new Estudiante("Javiera Galarce", "javiera@untec.cl");
        javiera.setCursos(new HashSet<>(Set.of(java, spring)));
        javiera = estudianteRepository.save(javiera);

        Estudiante matias = new Estudiante("Matias Rojas", "matias@untec.cl");
        matias.setCursos(new HashSet<>(Set.of(java, frontend)));
        matias = estudianteRepository.save(matias);

        Estudiante camila = new Estudiante("Camila Soto", "camila@untec.cl");
        camila.setCursos(new HashSet<>(Set.of(spring, frontend)));
        camila = estudianteRepository.save(camila);

        evaluacionRepository.save(new Evaluacion(javiera, java, TipoEvaluacion.PRACTICA, 6.5, LocalDate.now().minusDays(10)));
        evaluacionRepository.save(new Evaluacion(javiera, spring, TipoEvaluacion.EVALUACION, 6.8, LocalDate.now().minusDays(2)));
        evaluacionRepository.save(new Evaluacion(matias, java, TipoEvaluacion.EVALUACION, 5.9, LocalDate.now().minusDays(5)));
        evaluacionRepository.save(new Evaluacion(camila, frontend, TipoEvaluacion.PRACTICA, 7.0, LocalDate.now().minusDays(1)));
    }
}
