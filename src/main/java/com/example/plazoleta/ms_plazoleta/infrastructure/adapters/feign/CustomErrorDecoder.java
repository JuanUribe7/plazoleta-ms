package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.feign;


import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (methodKey.contains("getRoleByUser") && response.status() == 404) {
            return new NotFoundException(ExceptionMessages.USER_NOT_FOUND);
        }
        return defaultDecoder.decode(methodKey, response);
    }
}