package com.example.veterinaria.service;

import com.example.veterinaria.dto.ServicioVeterinarioDTO;
import com.example.veterinaria.model.ServicioVeterinario;
import com.example.veterinaria.repository.ServicioVeterinarioRepositorio;
import com.example.veterinaria.exception.ServicioNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioVeterinarioServicioImp implements ServicioVeterinarioServicio {

    private final ServicioVeterinarioRepositorio servicioVeterinarioRepositorio;

    public ServicioVeterinarioServicioImp(ServicioVeterinarioRepositorio servicioVeterinarioRepositorio) {
        this.servicioVeterinarioRepositorio = servicioVeterinarioRepositorio;
    }

    @Override
    public List<ServicioVeterinarioDTO> getAllServicios() {
        return servicioVeterinarioRepositorio.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ServicioVeterinarioDTO getServicioById(Long id) {
        //  excepción
        ServicioVeterinario servicio = servicioVeterinarioRepositorio.findById(id)
                .orElseThrow(() -> new ServicioNotFoundException("Servicio no encontrado con id: " + id));
        return convertToDTO(servicio);  // Convertir y retornar el DTO
    }

    @Override
    public ServicioVeterinarioDTO createServicio(ServicioVeterinarioDTO servicioDTO) {
        // Convertir DTO a entidad y guardar
        ServicioVeterinario servicio = convertToEntity(servicioDTO);
        ServicioVeterinario savedServicio = servicioVeterinarioRepositorio.save(servicio);
        return convertToDTO(savedServicio);  // Retornar el DTO guardado
    }

    @Override
    public ServicioVeterinarioDTO updateServicio(Long id, ServicioVeterinarioDTO servicioDTO) {
        // Verificar el servicio 
        if (!servicioVeterinarioRepositorio.existsById(id)) {
            throw new ServicioNotFoundException("Servicio no encontrado con id: " + id);  // Lanzar excepción si no existe
        }
        // Convertir y actualizar 
        ServicioVeterinario servicio = convertToEntity(servicioDTO);
        servicio.setId(id);  // Establecer id
        ServicioVeterinario updatedServicio = servicioVeterinarioRepositorio.save(servicio);
        return convertToDTO(updatedServicio);  // Retornar el DTO actualizado
    }

    @Override
    public void deleteServicio(Long id) {
        // Verificar si el servicio existe antes de eliminar
        if (!servicioVeterinarioRepositorio.existsById(id)) {
            throw new ServicioNotFoundException("Servicio no encontrado con id: " + id);  //  excepción si no existe
        }
        servicioVeterinarioRepositorio.deleteById(id);  // Eliminar el servicio
    }

    @Override
    public boolean existsById(Long id) {
        return servicioVeterinarioRepositorio.existsById(id);  
    }

    // Convertir de entidad a DTO
    private ServicioVeterinarioDTO convertToDTO(ServicioVeterinario servicio) {
        return new ServicioVeterinarioDTO(servicio.getNombre(), servicio.getDescripcion(), servicio.getCosto());
    }

    // Convertir de DTO a entidad
    private ServicioVeterinario convertToEntity(ServicioVeterinarioDTO servicioDTO) {
        return new ServicioVeterinario(servicioDTO.getNombre(), servicioDTO.getDescripcion(), servicioDTO.getCosto());
    }
}
