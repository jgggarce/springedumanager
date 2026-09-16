package com.alkemy.springedumanager.controller.api;

import com.alkemy.springedumanager.model.Estudiante;
import com.alkemy.springedumanager.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteRestController {

    private final EstudianteService estudianteService;

    public EstudianteRestController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public List<Estudiante> listar() {
        return estudianteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtener(@PathVariable Long id) {
        return estudianteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estudiante> crear(@Valid @RequestBody Estudiante estudiante) {
        Estudiante creado = estudianteService.guardar(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Long id, @Valid @RequestBody Estudiante datos) {
        return estudianteService.buscarPorId(id)
                .map(existente -> {
                    existente.setNombre(datos.getNombre());
                    existente.setCorreo(datos.getCorreo());
                    return ResponseEntity.ok(estudianteService.guardar(existente));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (estudianteService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
