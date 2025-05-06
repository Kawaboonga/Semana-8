package com.example.veterinaria.controller;

import com.example.veterinaria.model.Factura;
import com.example.veterinaria.service.FacturaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.Optional;

@RestController
@RequestMapping("/api/facturas/extra")  // Ruta específica para operaciones adicionales relacionadas con facturas
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaServicio facturaServicio;

    // Obtener facturas que han sido pagadas
    @GetMapping("/pagadas")
    public ResponseEntity<List<Factura>> getFacturasPagadas() {
        List<Factura> facturasPagadas = facturaServicio.getFacturasPagadas();
        if (facturasPagadas.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si no hay facturas pagadas
        }
        return ResponseEntity.ok(facturasPagadas);  // 200 OK con la lista de facturas pagadas
    }

    // Obtener facturas por fecha
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Factura>> getFacturasByFecha(@PathVariable String fecha) {
        List<Factura> facturas = facturaServicio.getFacturasByFecha(fecha);
        if (facturas.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si no hay facturas en esa fecha
        }
        return ResponseEntity.ok(facturas);  // 200 OK con las facturas correspondientes a la fecha
    }
}
