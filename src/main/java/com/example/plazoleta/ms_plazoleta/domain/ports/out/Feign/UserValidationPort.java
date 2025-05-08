package com.example.plazoleta.ms_plazoleta.domain.ports.out.feign;

public interface UserValidationPort {

    void validateOwnerExists(Long ownerId);
    String getPhoneByUserId(Long id);

}