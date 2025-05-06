package com.example.veterinaria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Date;

@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre de la veterinaria no puede ser nulo.")
    @Size(min = 1, max = 100, message = "El nombre de la veterinaria debe tener entre 1 y 100 caracteres.")
    private String veterinaria;

    @NotNull(message = "El nombre del cliente no puede ser nulo.")
    @Size(min = 1, max = 100, message = "El nombre del cliente debe tener entre 1 y 100 caracteres.")
    private String clienteNombre;

    @NotNull(message = "El RUT del cliente no puede ser nulo.")
    @Size(min = 1, max = 20, message = "El RUT del cliente debe tener entre 1 y 20 caracteres.")
    private String rutCliente;

    @NotNull(message = "El teléfono del cliente no puede ser nulo.")
    @Size(min = 1, max = 20, message = "El teléfono debe tener entre 1 y 20 caracteres.")
    private String telefono;

    @NotNull(message = "El nombre de la mascota no puede ser nulo.")
    @Size(min = 1, max = 100, message = "El nombre de la mascota debe tener entre 1 y 100 caracteres.")
    private String mascotaNombre;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicioVeterinario> servicios;

    @NotNull(message = "El total de la factura no puede ser nulo.")
    private double total;

    private boolean pagada;

    // Fecha de la factura
    @Temporal(TemporalType.DATE)
    private Date fecha;

    // Calcula el total basado en los servicios
    @PrePersist
    @PreUpdate
    private void calcularTotal() {
        total = servicios.stream().mapToDouble(ServicioVeterinario::getCosto).sum();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVeterinaria() {
        return veterinaria;
    }

    public void setVeterinaria(String veterinaria) {
        this.veterinaria = veterinaria;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMascotaNombre() {
        return mascotaNombre;
    }

    public void setMascotaNombre(String mascotaNombre) {
        this.mascotaNombre = mascotaNombre;
    }

    public List<ServicioVeterinario> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioVeterinario> servicios) {
        this.servicios = servicios;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
