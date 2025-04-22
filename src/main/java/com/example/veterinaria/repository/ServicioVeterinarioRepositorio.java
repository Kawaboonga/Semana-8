package com.example.veterinaria.repository;

import com.example.veterinaria.model.ServicioVeterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioVeterinarioRepositorio extends JpaRepository<ServicioVeterinario, Long> {
}
