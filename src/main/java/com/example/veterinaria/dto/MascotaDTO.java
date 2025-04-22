package com.example.veterinaria.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MascotaDTO {

    @NotNull
    @Size(min = 1, max = 100)
    private String nombre;

    @NotNull
    @Size(min = 1, max = 50)
    private String tipo; // Ej. Perro, Gato

    @NotNull
    private int edad;

    // Constructor por defecto
    public MascotaDTO() {
    }

    // Constructor para inicialización
    public MascotaDTO(String nombre, String tipo, int edad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.edad = edad;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
