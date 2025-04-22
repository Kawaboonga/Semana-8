package com.example.veterinaria.controller;

import com.example.veterinaria.dto.ServicioVeterinarioDTO;
import com.example.veterinaria.service.ServicioVeterinarioServicio;
import jakarta.validation.Valid;  // Usamos jakarta.validation en lugar de javax
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioVeterinarioController {

    private static final Logger logger = LoggerFactory.getLogger(ServicioVeterinarioController.class);  // Logger de SLF4J

    private final ServicioVeterinarioServicio servicioVeterinarioServicio;

    // Constructor de inyección de dependencias
    public ServicioVeterinarioController(ServicioVeterinarioServicio servicioVeterinarioServicio) {
        this.servicioVeterinarioServicio = servicioVeterinarioServicio;
    }

    // Obtener todos los servicios veterinarios
    @GetMapping
    public ResponseEntity<List<ServicioVeterinarioDTO>> getAllServicios() {
        List<ServicioVeterinarioDTO> servicios = servicioVeterinarioServicio.getAllServicios();
        if (servicios.isEmpty()) {
            logger.info("No se encontraron servicios veterinarios.");
            return ResponseEntity.noContent().build(); // Retorna 204 No Content si no hay servicios
        }
        logger.info("Se encontraron {} servicios veterinarios.", servicios.size());
        return ResponseEntity.ok(servicios); // Retorna 200 OK con la lista de servicios
    }

    // Obtener un servicio veterinario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ServicioVeterinarioDTO> getServicioById(@PathVariable Long id) {
        ServicioVeterinarioDTO servicio = servicioVeterinarioServicio.getServicioById(id);
        if (servicio == null) {
            logger.error("Servicio veterinario con ID {} no encontrado.", id);
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no se encuentra el servicio
        }
        logger.info("Servicio veterinario encontrado: {}", servicio.getNombre());
        return ResponseEntity.ok(servicio); // Retorna 200 OK con el servicio encontrado
    }

    // Crear un nuevo servicio veterinario
    @PostMapping
    public ResponseEntity<ServicioVeterinarioDTO> createServicio(@RequestBody @Valid ServicioVeterinarioDTO servicioDTO) {
        ServicioVeterinarioDTO nuevoServicio = servicioVeterinarioServicio.createServicio(servicioDTO);
        logger.info("Nuevo servicio veterinario creado: {}", nuevoServicio.getNombre());
        return ResponseEntity.status(201).body(nuevoServicio); // Retorna 201 Created con el servicio recién creado
    }

    // Actualizar un servicio veterinario existente
    @PutMapping("/{id}")
    public ResponseEntity<ServicioVeterinarioDTO> updateServicio(@PathVariable Long id, @RequestBody @Valid ServicioVeterinarioDTO servicioDTO) {
        ServicioVeterinarioDTO servicioActualizado = servicioVeterinarioServicio.updateServicio(id, servicioDTO);
        if (servicioActualizado == null) {
            logger.error("Servicio veterinario con ID {} no encontrado para actualizar.", id);
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no se encuentra el servicio a actualizar
        }
        logger.info("Servicio veterinario con ID {} actualizado.", id);
        return ResponseEntity.ok(servicioActualizado); // Retorna 200 OK con el servicio actualizado
    }

    // Eliminar un servicio veterinario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Long id) {
        if (!servicioVeterinarioServicio.existsById(id)) {
            logger.error("Servicio veterinario con ID {} no encontrado para eliminar.", id);
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no existe el servicio para eliminar
        }
        servicioVeterinarioServicio.deleteServicio(id); // Elimina el servicio
        logger.info("Servicio veterinario con ID {} eliminado correctamente.", id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content indicando que el servicio ha sido eliminado correctamente
    }
}
