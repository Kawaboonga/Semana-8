package com.example.veterinaria.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class ServicioVeterinarioDTO {

    @NotNull(message = "El nombre del servicio no puede ser nulo.")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres.")
    private String nombre;

    @NotNull(message = "La descripción del servicio no puede ser nula.")
    @Size(min = 1, max = 255, message = "La descripción debe tener entre 1 y 255 caracteres.")
    private String descripcion;

    @NotNull(message = "El costo del servicio no puede ser nulo.")
    @Positive(message = "El costo debe ser un valor positivo.")  // Reemplazado
    private double costo;

    // Constructor por defecto
    public ServicioVeterinarioDTO() {
    }

    // Constructor para inicializar el DTO
    public ServicioVeterinarioDTO(String nombre, String descripcion, double costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
