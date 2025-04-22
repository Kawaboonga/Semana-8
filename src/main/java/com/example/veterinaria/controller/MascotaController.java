package com.example.veterinaria.controller;

import com.example.veterinaria.dto.MascotaDTO;
import com.example.veterinaria.service.MascotaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;  
import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaServicio mascotaServicio;

    public MascotaController(MascotaServicio mascotaServicio) {
        this.mascotaServicio = mascotaServicio;
    }

    // Obtener todas las mascotas
    @GetMapping
    public ResponseEntity<List<MascotaDTO>> getAllMascotas() {
        List<MascotaDTO> mascotas = mascotaServicio.getAllMascotas();
        if (mascotas.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si no hay mascotas
        }
        return ResponseEntity.ok(mascotas);  // 200 OK con la lista de mascotas
    }

    // Obtener una mascota por ID
    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> getMascotaById(@PathVariable Long id) {
        MascotaDTO mascota = mascotaServicio.getMascotaById(id);
        if (mascota == null) {
            return ResponseEntity.notFound().build();  // 404 Not Found si no se encuentra la mascota
        }
        return ResponseEntity.ok(mascota);  // 200 OK con la mascota encontrada
    }

    // Crear una nueva mascota
    @PostMapping
    public ResponseEntity<MascotaDTO> createMascota(@RequestBody @Valid MascotaDTO mascotaDTO) {
        MascotaDTO nuevaMascota = mascotaServicio.createMascota(mascotaDTO);
        return ResponseEntity.status(201).body(nuevaMascota);  // 201 Created con la nueva mascota creada
    }

    // Actualizar una mascota existente
    @PutMapping("/{id}")
    public ResponseEntity<MascotaDTO> updateMascota(@PathVariable Long id, @RequestBody @Valid MascotaDTO mascotaDTO) {
        MascotaDTO mascotaActualizada = mascotaServicio.updateMascota(id, mascotaDTO);
        if (mascotaActualizada == null) {
            return ResponseEntity.notFound().build();  // 404 Not Found si la mascota no existe para actualizar
        }
        return ResponseEntity.ok(mascotaActualizada);  // 200 OK con la mascota actualizada
    }

    // Eliminar una mascota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable Long id) {
        if (!mascotaServicio.existsById(id)) {
            return ResponseEntity.notFound().build();  // 404 Not Found si la mascota no existe
        }
        mascotaServicio.deleteMascota(id);
        return ResponseEntity.noContent().build();  // 204 No Content indicando que la mascota fue eliminada correctamente
    }
}
