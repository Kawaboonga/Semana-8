package com.example.veterinaria.exception;

public class FacturaNotFoundException extends RuntimeException {
    public FacturaNotFoundException(String message) {
        super(message);
    }
}
