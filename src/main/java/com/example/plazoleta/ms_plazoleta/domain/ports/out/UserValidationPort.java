package com.example.plazoleta.ms_plazoleta.domain.ports.out;

public interface UserValidationPort {

    void validateOwnerExists(Long ownerId);

}