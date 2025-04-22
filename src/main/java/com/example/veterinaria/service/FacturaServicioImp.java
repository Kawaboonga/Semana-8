package com.example.veterinaria.service;

import com.example.veterinaria.model.Factura;
import com.example.veterinaria.repository.FacturaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServicioImp implements FacturaServicio {

    @Autowired
    private FacturaRepositorio facturaRepositorio;

    @Override
    public List<Factura> getAllFacturas() {
        return facturaRepositorio.findAll();
    }

    @Override
    public Optional<Factura> getFacturaById(Long id) {
        return facturaRepositorio.findById(id);
    }

    @Override
    public Factura createFactura(Factura factura) {
        return facturaRepositorio.save(factura);
    }

    @Override
    public Factura updateFactura(Long id, Factura factura) {
        if(facturaRepositorio.existsById(id)) {  // Verifica si la factura existe
            factura.setId(id);
            return facturaRepositorio.save(factura);
        }
        return null;
    }

    @Override
    public void deleteFactura(Long id) {
        if(facturaRepositorio.existsById(id)) {
            facturaRepositorio.deleteById(id);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return facturaRepositorio.existsById(id);  // Llama  método repo verificar si la factura existe
    }
}
