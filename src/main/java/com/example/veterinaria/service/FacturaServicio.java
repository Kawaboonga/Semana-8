package com.example.veterinaria.service;

import com.example.veterinaria.model.Factura;
import java.util.List;
import java.util.Optional;

public interface FacturaServicio {
    List<Factura> getAllFacturas();
    Optional<Factura> getFacturaById(Long id);
    Factura createFactura(Factura factura);
    Factura updateFactura(Long id, Factura factura);
    void deleteFactura(Long id);
    boolean existsById(Long id);  
}


