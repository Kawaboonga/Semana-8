package com.example.veterinaria.service;

import com.example.veterinaria.dto.ClienteDTO;
import com.example.veterinaria.model.Cliente;
import com.example.veterinaria.repository.ClienteRepositorio;
import com.example.veterinaria.exception.ClienteNotFoundException;  
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServicioImp implements ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    public ClienteServicioImp(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public List<ClienteDTO> getAllClientes() {
        return clienteRepositorio.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO getClienteById(Long id) {
        Optional<Cliente> clienteOptional = clienteRepositorio.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new ClienteNotFoundException("Cliente no encontrado con id: " + id);
        }
        return convertToDTO(clienteOptional.get());
    }

    @Override
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        Cliente savedCliente = clienteRepositorio.save(cliente);
        return convertToDTO(savedCliente);
    }

    @Override
    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        if (!clienteRepositorio.existsById(id)) {
            throw new ClienteNotFoundException("Cliente no encontrado con id: " + id);
        }
        Cliente cliente = convertToEntity(clienteDTO);
        cliente.setId(id);
        Cliente updatedCliente = clienteRepositorio.save(cliente);
        return convertToDTO(updatedCliente);
    }

    @Override
    public void deleteCliente(Long id) {
        if (!clienteRepositorio.existsById(id)) {
            throw new ClienteNotFoundException("Cliente no encontrado con id: " + id);
        }
        clienteRepositorio.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return clienteRepositorio.existsById(id);
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getRut(), cliente.getTelefono());
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNombre(), clienteDTO.getRut(), clienteDTO.getTelefono());
    }
}
