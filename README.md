# SpringEduManager

Aplicación web para que la Coordinación Académica de un bootcamp gestione estudiantes, cursos y sus evaluaciones/prácticas desde una sola plataforma.

## Tecnologías utilizadas

- Java 17 con Spring Boot 3.2
- Spring MVC + Thymeleaf (vistas web)
- Spring Data JPA + H2 (persistencia)
- Spring Security (login/logout y roles ADMIN/USER)
- Spring Web (API REST)
- Maven como gestor de dependencias

## Funcionalidades

- Login de usuario (ADMIN o USER)
- Gestionar estudiantes (crear, listar, editar)
- Gestionar cursos (crear, listar) — creación restringida al rol ADMIN
- Registrar evaluaciones y prácticas de los estudiantes
- Consultar evaluaciones por estudiante y por curso
- API REST para estudiantes y cursos

## Estructura del proyecto

```
src/main/java/com/alkemy/springedumanager/
  model/         Estudiante, Curso, Evaluacion, TipoEvaluacion (enum)
  repository/    EstudianteRepository, CursoRepository, EvaluacionRepository
  service/       EstudianteService, CursoService, EvaluacionService
  controller/    HomeController, EstudianteController, CursoController, EvaluacionController
  controller/api/EstudianteRestController, CursoRestController
  config/        SecurityConfig, DataSeeder, StringToCursoConverter, StringToEstudianteConverter
src/main/resources/
  application.properties
  templates/     login.html, home.html, estudiantes/, cursos/, evaluaciones/, fragments/nav.html
  static/css/style.css
```

## Cómo ejecutarlo

1. Importar el proyecto en Eclipse (File → Import → Maven → Existing Maven Projects).
2. Clic derecho sobre el proyecto → Run As → Spring Boot App (o desde Terminal: `mvn spring-boot:run`).
3. La primera vez, Maven descarga automáticamente las dependencias (se necesita conexión a internet).
4. Se abre en el navegador en `http://localhost:8081/`.

## Usuarios de prueba

- Administrador (puede crear cursos): usuario `admin`, contraseña `admin123`
- Estudiante (solo lectura/registro): usuario `estudiante`, contraseña `user123`
