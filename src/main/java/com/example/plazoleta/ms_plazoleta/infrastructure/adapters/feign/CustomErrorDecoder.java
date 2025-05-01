package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.feign;

import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.OwnerNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (methodKey.contains("getRoleByUser") && response.status() == 404) {
            return new OwnerNotFoundException("El propietario no fue encontrado.");
        }
        return defaultDecoder.decode(methodKey, response);
    }
}