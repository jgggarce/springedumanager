package com.alkemy.springedumanager.controller;

import com.alkemy.springedumanager.model.Curso;
import com.alkemy.springedumanager.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "cursos/list";
    }

  
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("curso", new Curso());
        return "cursos/form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/nuevo")
    public String guardar(@Valid @ModelAttribute("curso") Curso curso, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "cursos/form";
        }
        cursoService.guardar(curso);
        return "redirect:/cursos";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return "redirect:/cursos";
    }
}
