package com.alkemy.springedumanager.controller;

import com.alkemy.springedumanager.model.Evaluacion;
import com.alkemy.springedumanager.model.TipoEvaluacion;
import com.alkemy.springedumanager.service.CursoService;
import com.alkemy.springedumanager.service.EstudianteService;
import com.alkemy.springedumanager.service.EvaluacionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;
    private final EstudianteService estudianteService;
    private final CursoService cursoService;

    public EvaluacionController(EvaluacionService evaluacionService,
                                 EstudianteService estudianteService,
                                 CursoService cursoService) {
        this.evaluacionService = evaluacionService;
        this.estudianteService = estudianteService;
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("evaluaciones", evaluacionService.listarTodas());
        return "evaluaciones/list";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("evaluacion", new Evaluacion());
        model.addAttribute("estudiantes", estudianteService.listarTodos());
        model.addAttribute("cursos", cursoService.listarTodos());
        model.addAttribute("tipos", TipoEvaluacion.values());
        return "evaluaciones/form";
    }

    @PostMapping("/nuevo")
    public String guardar(@Valid @ModelAttribute("evaluacion") Evaluacion evaluacion,
                           BindingResult resultado,
                           Model model) {
        if (resultado.hasErrors()) {
            model.addAttribute("estudiantes", estudianteService.listarTodos());
            model.addAttribute("cursos", cursoService.listarTodos());
            model.addAttribute("tipos", TipoEvaluacion.values());
            return "evaluaciones/form";
        }
        evaluacionService.guardar(evaluacion);
        return "redirect:/evaluaciones";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        evaluacionService.eliminar(id);
        return "redirect:/evaluaciones";
    }
}
