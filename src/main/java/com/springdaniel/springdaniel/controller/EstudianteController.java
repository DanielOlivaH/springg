package com.springdaniel.springdaniel.controller;

import com.springdaniel.springdaniel.model.Estudiante;
import com.springdaniel.springdaniel.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estudiante crear(@Valid @RequestBody Estudiante estudiante) {
        return service.guardarEstudiante(estudiante);
    }

    @GetMapping
    public List<Estudiante> listarTodos() {
        return service.obtenerTodos();
    }

    //Obtener estudiante por ID
    @GetMapping("/{id}")
    public Estudiante obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // NUEVO: Actualizar estudiante (PUT completo)
    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable Long id, @Valid @RequestBody Estudiante estudiante) {
        return service.actualizarEstudiante(id, estudiante);
    }

    //Actualización parcial (PATCH) - opcional pero útil
    @PatchMapping("/{id}")
    public Estudiante actualizarParcial(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        // Implementación similar a PUT pero solo actualiza campos no nulos
        Estudiante existente = service.obtenerPorId(id);
        if (estudiante.getNombre() != null) existente.setNombre(estudiante.getNombre());
        if (estudiante.getEmail() != null) existente.setEmail(estudiante.getEmail());
        if (estudiante.getEdad() != 0) existente.setEdad(estudiante.getEdad());
        return service.guardarEstudiante(existente);
    }

    //Eliminar estudiante
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminarEstudiante(id);
    }
}