package com.example.veterinaria.repository;

import com.example.veterinaria.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota, Long> {
}
