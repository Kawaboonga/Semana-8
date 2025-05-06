package com.example.veterinaria.repository;

import com.example.veterinaria.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FacturaRepositorio extends JpaRepository<Factura, Long> {

    // Obtener todas las facturas pagadas o no pagadas
    List<Factura> findByPagada(boolean pagada);

    // Obtener todas las facturas de un cliente específico por RUT
    List<Factura> findByRutCliente(String rutCliente);

    // Obtener las facturas por fecha (usando LocalDate)
    List<Factura> findByFecha(LocalDate fecha);
}
