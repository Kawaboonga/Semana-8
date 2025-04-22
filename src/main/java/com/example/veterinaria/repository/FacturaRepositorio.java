package com.example.veterinaria.repository;

import com.example.veterinaria.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacturaRepositorio extends JpaRepository<Factura, Long> {

    // Método para obtener todas las facturas pagadas
    List<Factura> findByPagada(boolean pagada);

    // Método para obtener todas las facturas de un cliente específico
    List<Factura> findByRutCliente(String rutCliente);
}
