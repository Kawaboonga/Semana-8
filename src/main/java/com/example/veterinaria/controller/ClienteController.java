package com.example.veterinaria.controller;

import com.example.veterinaria.dto.ClienteDTO;
import com.example.veterinaria.service.ClienteServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Validated
public class ClienteController {

    private final ClienteServicio clienteServicio;

    public ClienteController(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> clientes = clienteServicio.getAllClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    // Obtener un cliente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        ClienteDTO cliente = clienteServicio.getClienteById(id);
        return cliente != null ? ResponseEntity.ok(cliente)
                               : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO nuevoCliente = clienteServicio.createCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO clienteActualizado = clienteServicio.updateCliente(id, clienteDTO);
        return clienteActualizado != null ? ResponseEntity.ok(clienteActualizado)
                                          : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (!clienteServicio.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        clienteServicio.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
