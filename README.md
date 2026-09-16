# SpringEduManager

Evaluación del Módulo 6 (Desarrollo de aplicaciones JEE con Spring Framework) del bootcamp Alkemy.

Aplicación web para que la Coordinación Académica de un bootcamp gestione estudiantes, cursos y sus evaluaciones/prácticas desde una sola plataforma.

## ⚠️ Importante: este proyecto no viene compilado ni probado

Se escribió sin poder compilarlo ni ejecutarlo, porque el entorno donde se generó no tiene acceso a Maven Central (bloqueado por política de red de ese entorno) y no se pudieron descargar las dependencias de Spring Boot para probarlo. El código sigue al pie de la letra la sintaxis y las convenciones estándar de Spring Boot 3 / Spring Security 6, pero **tienes que compilarlo y probarlo tú misma** en tu computador, donde Maven sí puede descargar todo automáticamente desde internet (a diferencia del proyecto de Biblioteca UNTEC, aquí no hay que bajar ningún `.jar` a mano: Maven lo hace solo).

## Tecnologías utilizadas

- Java 17+ con Spring Boot 3.2
- Maven como gestor de dependencias
- Spring MVC + Thymeleaf (vistas web)
- Spring Data JPA + H2 (persistencia)
- Spring Security (login/logout y roles ADMIN/USER)
- Spring Web (API REST)

## Estructura del proyecto

```
src/main/java/com/alkemy/springedumanager/
  model/         Estudiante, Curso, Evaluacion, TipoEvaluacion (enum)
  repository/    EstudianteRepository, CursoRepository, EvaluacionRepository
  service/       EstudianteService, CursoService, EvaluacionService
  controller/    HomeController, EstudianteController, CursoController,
                 EvaluacionController (vistas web con Thymeleaf)
  controller/api/EstudianteRestController, CursoRestController (API REST)
  config/        SecurityConfig, DataSeeder, StringToCursoConverter,
                 StringToEstudianteConverter
src/main/resources/
  application.properties
  templates/     login.html, home.html, estudiantes/, cursos/, evaluaciones/, fragments/nav.html
  static/css/style.css
```

## Cómo se relaciona con las 5 lecciones del módulo

- **Lección 1 (gestor de proyectos):** `pom.xml` con Maven y las dependencias de web, JPA, security y H2.
- **Lección 2 (Spring MVC):** entidades `Estudiante` y `Curso`, controladores `@Controller` con `@GetMapping`/`@PostMapping`, formularios y listados en Thymeleaf.
- **Lección 3 (acceso a datos):** `EstudianteRepository`/`CursoRepository`/`EvaluacionRepository` extendiendo `JpaRepository`, capa de `@Service`, base de datos H2 embebida.
- **Lección 4 (Spring Security):** `SecurityConfig`, usuarios definidos en `application.properties`, login/logout funcional, ruta de creación de cursos protegida para el rol ADMIN (`@PreAuthorize` + configuración en `SecurityConfig`).
- **Lección 5 (interoperabilidad/REST):** `EstudianteRestController` y `CursoRestController` con operaciones CRUD completas (`GET`/`POST`/`PUT`/`DELETE`), aseguradas con autenticación (JWT quedó fuera del alcance por ser opcional en la consigna).

## Cómo ejecutarlo

1. Necesitas tener **Java 17 o superior** y (opcionalmente) **Maven** instalados. Si usas Eclipse con soporte Maven (m2e), no necesitas instalar Maven aparte.
2. Importa el proyecto:
   - En Eclipse: **File → Import → Maven → Existing Maven Projects**, selecciona esta carpeta.
   - O bien, en Terminal: `cd springedumanager && mvn spring-boot:run`
3. La primera vez, Maven va a descargar automáticamente todas las dependencias (Spring Boot, H2, Thymeleaf, etc.) — necesitas conexión a internet solo para ese primer arranque.
4. La app queda disponible en `http://localhost:8081/` (se usó el puerto 8081, no el 8080, para no chocar con otros proyectos Tomcat que tengas corriendo).
5. Se abre la pantalla de login.

## Usuarios de prueba

- **Administrador** (puede crear cursos): usuario `admin`, contraseña `admin123`
- **Estudiante** (solo lectura/registro): usuario `estudiante`, contraseña `user123`

## Datos de ejemplo

Al arrancar por primera vez se cargan solos 3 cursos, 3 estudiantes y algunas evaluaciones/prácticas de ejemplo (ver `DataSeeder.java`), para poder probar todo sin cargar nada a mano.

## Probar la API REST

Con la app corriendo, desde Postman (o `curl`) usando autenticación **Basic Auth** con cualquiera de los usuarios de arriba:

```
GET    http://localhost:8081/api/estudiantes
GET    http://localhost:8081/api/estudiantes/1
POST   http://localhost:8081/api/estudiantes      (requiere rol ADMIN)
PUT    http://localhost:8081/api/estudiantes/1    (requiere rol ADMIN)
DELETE http://localhost:8081/api/estudiantes/1    (requiere rol ADMIN)

GET    http://localhost:8081/api/cursos
POST   http://localhost:8081/api/cursos           (requiere rol ADMIN)
```

Ejemplo de cuerpo JSON para crear un estudiante:

```json
{
  "nombre": "Pedro Pérez",
  "correo": "pedro@untec.cl"
}
```

## Consola H2 (revisar los datos guardados)

Con la app corriendo, entra a `http://localhost:8081/h2-console` con la URL JDBC `jdbc:h2:file:~/springedumanager/eduDb` (usuario `sa`, sin contraseña) para ver las tablas y los datos directamente.

## Capturas

*(agregar aquí: login, listado de estudiantes, listado de cursos, formulario protegido de "nuevo curso" con el usuario admin, listado de evaluaciones, y una prueba del endpoint REST en Postman)*

## Autor

Javiera Galarce — Bootcamp Alkemy, Módulo 6
