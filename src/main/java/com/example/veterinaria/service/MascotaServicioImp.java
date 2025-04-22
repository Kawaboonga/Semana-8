package com.example.veterinaria.service;

import com.example.veterinaria.dto.MascotaDTO;
import com.example.veterinaria.model.Mascota;
import com.example.veterinaria.repository.MascotaRepositorio;
import com.example.veterinaria.exception.MascotaNotFoundException;  
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MascotaServicioImp implements MascotaServicio {

    private final MascotaRepositorio mascotaRepositorio;

    public MascotaServicioImp(MascotaRepositorio mascotaRepositorio) {
        this.mascotaRepositorio = mascotaRepositorio;
    }

    @Override
    public List<MascotaDTO> getAllMascotas() {
        return mascotaRepositorio.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MascotaDTO getMascotaById(Long id) {
        Optional<Mascota> mascota = mascotaRepositorio.findById(id);
        if (mascota.isPresent()) {
            return convertToDTO(mascota.get());
        } else {
            throw new MascotaNotFoundException("Mascota no encontrada con id: " + id);
        }
    }

    @Override
    public MascotaDTO createMascota(MascotaDTO mascotaDTO) {
        Mascota mascota = convertToEntity(mascotaDTO);
        Mascota savedMascota = mascotaRepositorio.save(mascota);
        return convertToDTO(savedMascota);
    }

    @Override
    public MascotaDTO updateMascota(Long id, MascotaDTO mascotaDTO) {
        if (!mascotaRepositorio.existsById(id)) {
            throw new MascotaNotFoundException("Mascota no encontrada con id: " + id);
        }
        Mascota mascota = convertToEntity(mascotaDTO);
        mascota.setId(id);
        Mascota updatedMascota = mascotaRepositorio.save(mascota);
        return convertToDTO(updatedMascota);
    }

    @Override
    public void deleteMascota(Long id) {
        if (!mascotaRepositorio.existsById(id)) {
            throw new MascotaNotFoundException("Mascota no encontrada con id: " + id);
        }
        mascotaRepositorio.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return mascotaRepositorio.existsById(id);
    }

    private MascotaDTO convertToDTO(Mascota mascota) {
        return new MascotaDTO(mascota.getNombre(), mascota.getTipo(), mascota.getEdad());
    }

    private Mascota convertToEntity(MascotaDTO mascotaDTO) {
        return new Mascota(mascotaDTO.getNombre(), mascotaDTO.getTipo(), mascotaDTO.getEdad());
    }
}
