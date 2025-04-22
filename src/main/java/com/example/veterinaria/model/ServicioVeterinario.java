package com.example.veterinaria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PositiveOrZero;

@Entity  // Marca esta clase como una entidad JPA
public class ServicioVeterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Generación automática del ID
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo.")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres.")
    private String nombre;

    @NotNull(message = "La descripción no puede ser nula.")
    @Size(min = 1, max = 255, message = "La descripción debe tener entre 1 y 255 caracteres.")
    private String descripcion;

    @NotNull(message = "El costo no puede ser nulo.")
    @PositiveOrZero(message = "El costo debe ser un valor positivo o cero.")
    private double costo;

    // Relación con la entidad Factura
    @ManyToOne
    @JoinColumn(name = "factura_id", referencedColumnName = "id")  // La columna factura_id es la clave foránea
    private Factura factura;  // Cada ServicioVeterinario está relacionado con una Factura

    // Constructor por defecto (requerido por JPA)
    public ServicioVeterinario() {}

    // Constructor para inicializar el objeto con parámetros
    public ServicioVeterinario(String nombre, String descripcion, double costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}
