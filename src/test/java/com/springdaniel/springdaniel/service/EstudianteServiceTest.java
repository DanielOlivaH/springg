package com.springdaniel.springdaniel.service;

import com.springdaniel.springdaniel.model.Estudiante;
import com.springdaniel.springdaniel.repository.EstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstudianteServiceTest {

    @Mock
    private EstudianteRepository repository;

    @InjectMocks
    private EstudianteService service;

    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombre("Juan Perez");
        estudiante.setEmail("juan@ejemplo.com");
        estudiante.setEdad(20);
    }

    @Test
    void testGuardarEstudiante() {
        when(repository.save(any(Estudiante.class))).thenReturn(estudiante);
        Estudiante guardado = service.guardarEstudiante(new Estudiante());
        assertNotNull(guardado);
        assertEquals("Pepe Perez", guardado.getNombre());
        verify(repository, times(1)).save(any(Estudiante.class));
    }

    @Test
    void testObtenerTodos() {
        when(repository.findAll()).thenReturn(List.of(estudiante));
        List<Estudiante> lista = service.obtenerTodos();
        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
        verify(repository, times(1)).findAll();
    }
}