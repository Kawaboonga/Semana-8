package com.example.veterinaria.controller;

import com.example.veterinaria.model.Factura;
import com.example.veterinaria.service.FacturaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaCtr {

    private final FacturaServicio facturaServicio;

    // Obtener todas las facturas
    @GetMapping
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> facturas = facturaServicio.getAllFacturas();
        if (facturas.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si no hay facturas
        }
        return ResponseEntity.ok(facturas);  // 200 OK con la lista de facturas
    }

    // Obtener una factura por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long id) {
        Optional<Factura> facturaOptional = facturaServicio.getFacturaById(id);  // Obtener el Optional<Factura>
        if (facturaOptional.isPresent()) {
            return ResponseEntity.ok(facturaOptional.get());  // Retorna la factura con HTTP 200 si está presente
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found si no se encuentra la factura
        }
    }
}
