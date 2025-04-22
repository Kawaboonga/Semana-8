package com.example.veterinaria.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClienteDTO {

    private Long id;

    @NotNull(message = "El nombre no puede ser nulo.")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres.")
    private String nombre;

    @NotNull(message = "El RUT no puede ser nulo.")
    @Size(min = 1, max = 20, message = "El RUT debe tener entre 1 y 20 caracteres.")
    @Pattern(regexp = "^([0-9]{7,8}-[0-9Kk])$", message = "El RUT debe tener un formato válido.")
    private String rut;

    @NotNull(message = "El teléfono no puede ser nulo.")
    @Size(min = 1, max = 20, message = "El teléfono debe tener entre 1 y 20 caracteres.")
    @Pattern(regexp = "^\\+?([0-9]{1,4})?([0-9]{9})$", message = "El teléfono debe tener un formato válido.")
    private String telefono;

    // Constructor para inicializar
    public ClienteDTO(Long id, String nombre, String rut, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
