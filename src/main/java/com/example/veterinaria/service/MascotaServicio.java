package com.example.veterinaria.service;

import com.example.veterinaria.dto.MascotaDTO;

import java.util.List;

public interface MascotaServicio {
    List<MascotaDTO> getAllMascotas();
    MascotaDTO getMascotaById(Long id);
    MascotaDTO createMascota(MascotaDTO mascotaDTO);
    MascotaDTO updateMascota(Long id, MascotaDTO mascotaDTO);
    void deleteMascota(Long id);
    boolean existsById(Long id);
}
