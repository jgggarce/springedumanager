package com.alkemy.springedumanager.controller;

import com.alkemy.springedumanager.model.Estudiante;
import com.alkemy.springedumanager.service.CursoService;
import com.alkemy.springedumanager.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;
    private final CursoService cursoService;

    public EstudianteController(EstudianteService estudianteService, CursoService cursoService) {
        this.estudianteService = estudianteService;
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("estudiantes", estudianteService.listarTodos());
        return "estudiantes/list";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("cursos", cursoService.listarTodos());
        return "estudiantes/form";
    }

    @PostMapping("/nuevo")
    public String guardar(@Valid @ModelAttribute("estudiante") Estudiante estudiante,
                           BindingResult resultado,
                           Model model) {
        if (resultado.hasErrors()) {
            model.addAttribute("cursos", cursoService.listarTodos());
            return "estudiantes/form";
        }
        estudianteService.guardar(estudiante);
        return "redirect:/estudiantes";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return "redirect:/estudiantes";
    }
}
