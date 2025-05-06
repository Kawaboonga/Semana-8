package com.example.veterinaria.service;


import com.example.veterinaria.model.Factura;
import com.example.veterinaria.repository.FacturaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacturaServicioImp implements FacturaServicio {

    private final FacturaRepositorio facturaRepository;

    @Override
    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public Optional<Factura> getFacturaById(Long id) {
        return facturaRepository.findById(id);
    }

    @Override
    public Factura createFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura updateFactura(Long id, Factura factura) {
        if (facturaRepository.existsById(id)) {
            factura.setId(id);
            return facturaRepository.save(factura);
        }
        return null;  // Or throw an exception if preferred
    }

    @Override
    public void deleteFactura(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return facturaRepository.existsById(id);
    }

    @Override
    public List<Factura> getFacturasByFecha(String fechaStr) {
    LocalDate fecha = LocalDate.parse(fechaStr);  // convierte String a LocalDate
    return facturaRepository.findByFecha(fecha);
    }

     // Implementación del método getFacturasPagadas
     @Override
     public List<Factura> getFacturasPagadas() {
         return facturaRepository.findByPagada(true);  // Filtra las facturas que están marcadas como pagadas
     }
}
