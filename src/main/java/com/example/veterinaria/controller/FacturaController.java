package com.example.veterinaria.controller;

import com.example.veterinaria.model.Factura;
import com.example.veterinaria.service.FacturaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaServicio facturaServicio;

    // Constructor para inyección de dependencias
    public FacturaController(FacturaServicio facturaServicio) {
        this.facturaServicio = facturaServicio;
    }

    // Obtener todas las facturas
    @GetMapping
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> facturas = facturaServicio.getAllFacturas();
        if (facturas.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content si no hay facturas
        }
        return ResponseEntity.ok(facturas); // Retorna 200 OK con la lista de facturas
    }

    // Obtener una factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long id) {
        Optional<Factura> factura = facturaServicio.getFacturaById(id);
        if (factura.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no se encuentra la factura
        }
        return ResponseEntity.ok(factura.get()); // Retorna 200 OK con la factura encontrada
    }

    // Crear una nueva factura
    @PostMapping
    public ResponseEntity<Factura> createFactura(@RequestBody Factura factura) {
        Factura nuevaFactura = facturaServicio.createFactura(factura);
        return ResponseEntity.status(201).body(nuevaFactura); // Retorna 201 Created con la nueva factura creada
    }

    // Actualizar una factura existente
    @PutMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Long id, @RequestBody Factura factura) {
        Factura facturaActualizada = facturaServicio.updateFactura(id, factura);
        if (facturaActualizada == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found si la factura no existe para actualizar
        }
        return ResponseEntity.ok(facturaActualizada); // Retorna 200 OK con la factura actualizada
    }

    // Eliminar una factura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id) {
        if (!facturaServicio.existsById(id)) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found si la factura no existe
        }
        facturaServicio.deleteFactura(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content para indicar que la factura fue eliminada correctamente
    }
}
