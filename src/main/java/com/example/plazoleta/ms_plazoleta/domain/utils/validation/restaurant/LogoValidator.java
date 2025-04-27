package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;


import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.IllegalLogoException;

public class LogoValidator {

    private LogoValidator() {
        throw new UnsupportedOperationException("Clase utilitaria, no debe instanciarse.");
    }

    public static void validate(String url) {


        if (url == null || url.trim().isEmpty()) {
            throw new IllegalLogoException("La URL del logo no puede estar vacía");
        }

        if (url.contains("localhost") || url.contains("127.0.0.1")) {
            throw new IllegalArgumentException("La URL del logo no puede ser local");
        }

        if (!url.matches("^(http|https)://[\\w\\-]+(\\.[\\w\\-]+)+[/#?]?.*$")) {
            throw new IllegalArgumentException("La URL del logo debe ser válida y comenzar con http o https");
        }



        if (!url.matches(".*\\.(png|jpg|jpeg|svg)$")) {
            throw new IllegalArgumentException("La URL del logo debe terminar en .png, .jpg, .jpeg o .svg");
        }
    }
}
