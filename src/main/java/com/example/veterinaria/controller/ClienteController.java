package com.example.veterinaria.controller;

import com.example.veterinaria.dto.ClienteDTO;
import com.example.veterinaria.service.ClienteServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;  
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteServicio clienteServicio;

    // Constructor de inyección de dependencias, adecuado y correcto
    public ClienteController(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> clientes = clienteServicio.getAllClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna código HTTP 204 si la lista está vacía
        }
        return ResponseEntity.ok(clientes); // Retorna la lista de clientes con código HTTP 200
    }

    // Obtener un cliente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
    ClienteDTO cliente = clienteServicio.getClienteById(id);
    if (cliente == null) {
        // Devolver un ResponseEntity con un mensaje o vació en el cuerpo
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Retorna 404 con cuerpo vacío
    }
    return ResponseEntity.ok(cliente);  // Retorna 200 con el cliente encontrado
}


    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO nuevoCliente = clienteServicio.createCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente); // Retorna código HTTP 201 y el nuevo cliente
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
    ClienteDTO clienteActualizado = clienteServicio.updateCliente(id, clienteDTO);
    if (clienteActualizado == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Retorna un 404 sin cuerpo
    }
    return ResponseEntity.ok(clienteActualizado);  // Retorna el cliente actualizado con HTTP 200
}


    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (!clienteServicio.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build(); // Retorna HTTP 404 si el cliente no existe
        }
        clienteServicio.deleteCliente(id);
        return ResponseEntity.noContent().build(); // Retorna HTTP 204 para indicar que el cliente fue eliminado correctamente
    }
}
