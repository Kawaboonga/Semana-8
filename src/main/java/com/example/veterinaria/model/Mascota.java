package com.example.veterinaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity  // Marca esta clase como una entidad JPA
public class Mascota {

    @Id  // Marca esta propiedad como la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Generación automática del ID
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo.")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres.")
    private String nombre;

    @NotNull(message = "El tipo de mascota no puede ser nulo.")
    @Size(min = 1, max = 50, message = "El tipo de mascota debe tener entre 1 y 50 caracteres.")
    private String tipo; // Ejemplo: Perro, Gato

    @NotNull(message = "La edad no puede ser nula.")
    private int edad;

    // Constructor por defecto
    public Mascota() {
    }

    // Constructor para inicialización
    public Mascota(String nombre, String tipo, int edad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.edad = edad;
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
