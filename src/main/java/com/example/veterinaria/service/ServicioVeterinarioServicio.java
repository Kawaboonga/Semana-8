package com.example.veterinaria.service;

import com.example.veterinaria.dto.ServicioVeterinarioDTO;

import java.util.List;

public interface ServicioVeterinarioServicio {
    List<ServicioVeterinarioDTO> getAllServicios();
    ServicioVeterinarioDTO getServicioById(Long id);
    ServicioVeterinarioDTO createServicio(ServicioVeterinarioDTO servicioDTO);
    ServicioVeterinarioDTO updateServicio(Long id, ServicioVeterinarioDTO servicioDTO);
    void deleteServicio(Long id);
    boolean existsById(Long id);
}
