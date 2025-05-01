package com.example.plazoleta.ms_plazoleta.domain.ports.out.Feign;

public interface UserValidationPort {

    void validateOwnerExists(Long ownerId);

}