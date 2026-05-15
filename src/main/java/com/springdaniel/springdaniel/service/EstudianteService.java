package com.springdaniel.springdaniel.service;

import com.springdaniel.springdaniel.model.Estudiante;
import com.springdaniel.springdaniel.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository repository;

    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    public List<Estudiante> obtenerTodos() {
        return repository.findAll();
    }

    //Obtener estudiante por ID (lanza excepción si no existe)
    public Estudiante obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }

    // Actualizar estudiante
    public Estudiante actualizarEstudiante(Long id, Estudiante estudianteActualizado) {
        Estudiante estudianteExistente = obtenerPorId(id);

        estudianteExistente.setNombre(estudianteActualizado.getNombre());
        estudianteExistente.setEmail(estudianteActualizado.getEmail());
        estudianteExistente.setEdad(estudianteActualizado.getEdad());

        return repository.save(estudianteExistente);
    }

    // Eliminar estudiante
    public void eliminarEstudiante(Long id) {
        Estudiante estudiante = obtenerPorId(id);
        repository.delete(estudiante);
    }
}