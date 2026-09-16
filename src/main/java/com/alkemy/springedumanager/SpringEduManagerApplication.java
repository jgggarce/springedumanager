package com.alkemy.springedumanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicacion SpringEduManager.
 *
 * <p>Evaluacion del Modulo 6 (Desarrollo de aplicaciones JEE con Spring
 * Framework) del bootcamp Alkemy. Permite a la Coordinacion Academica
 * gestionar estudiantes, cursos y evaluaciones/practicas desde una unica
 * plataforma web.</p>
 */
@SpringBootApplication
public class SpringEduManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEduManagerApplication.class, args);
    }
}
