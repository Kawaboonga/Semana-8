package com.example.veterinaria.exception;

public class MascotaNotFoundException extends RuntimeException {
    public MascotaNotFoundException(String message) {
        super(message);
    }
}