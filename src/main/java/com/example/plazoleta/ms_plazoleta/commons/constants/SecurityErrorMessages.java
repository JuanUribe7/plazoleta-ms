package com.example.plazoleta.ms_plazoleta.commons.constants;

public class SecurityErrorMessages {

    public static final String UNAUTHORIZED_JSON    = "{\"error\": \"No autorizado. Proporcione un token válido.\"}";
    public static final String ACCESS_DENIED_JSON  = "{\"error\": \"Acceso denegado. No tienes permisos suficientes.\"}";
    public static final String TOKEN_INVALID_LOG    = "Token inválido o error al procesar: ";

    private SecurityErrorMessages() {}
}
