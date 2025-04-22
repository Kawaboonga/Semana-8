package com.example.veterinaria.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

public class FacturaDTO {

    @NotNull
    @Size(min = 1, max = 100)
    private String veterinaria;

    @NotNull
    @Size(min = 1, max = 100)
    private String clienteNombre;

    @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^([0-9]{7,8}-[0-9Kk])$", message = "El RUT debe tener un formato válido.")
    private String rutCliente;  // Validación para RUT chileno

    @NotNull
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^\\+?([0-9]{1,4})?([0-9]{9})$", message = "El teléfono debe tener un formato válido.")
    private String telefono;  // Validación para teléfono

    @NotNull
    @Size(min = 1, max = 100)
    private String mascotaNombre;

    @NotNull
    @Size(min = 1)  // Asegura que la lista no esté vacía
    private List<ServicioVeterinarioDTO> servicios;

    @NotNull
    @PositiveOrZero(message = "El total debe ser un valor positivo o cero.")
    private double total;  // Asegura que el total no sea negativo

    private boolean pagada;

    // Getters y setters
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

    public List<ServicioVeterinarioDTO> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioVeterinarioDTO> servicios) {
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
}
