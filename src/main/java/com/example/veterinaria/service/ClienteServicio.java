package com.example.veterinaria.service;

import com.example.veterinaria.dto.ClienteDTO;
import java.util.List;

public interface ClienteServicio {
    List<ClienteDTO> getAllClientes();
    ClienteDTO getClienteById(Long id);
    ClienteDTO createCliente(ClienteDTO clienteDTO);
    ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO);
    void deleteCliente(Long id);
    boolean existsById(Long id);
}