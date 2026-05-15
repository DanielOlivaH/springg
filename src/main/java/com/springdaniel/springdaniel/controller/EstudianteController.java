package com.springdaniel.springdaniel.controller;

import com.springdaniel.springdaniel.model.Estudiante;
import com.springdaniel.springdaniel.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService service;

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante estudiante) {
        return service.guardarEstudiante(estudiante);
    }

    @GetMapping
    public List<Estudiante> listarTodos() {
        return service.obtenerTodos();
    }
}