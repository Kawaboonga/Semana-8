package com.example.veterinaria.repository;

import com.example.veterinaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    // Método para buscar un cliente por su RUT
    Optional<Cliente> findByRut(String rut);

    // Puedes agregar más consultas personalizadas usando la anotación @Query si lo necesitas
}
