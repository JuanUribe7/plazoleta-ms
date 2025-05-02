package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.feign;

import com.example.plazoleta.ms_plazoleta.domain.ports.out.Feign.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import feign.FeignException;
import org.springframework.stereotype.Component;

@Component
public class UserFeignAdapter implements UserValidationPort {

    private final UserFeignClient userFeignClient;

    public UserFeignAdapter(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }


    @Override
    public void validateOwnerExists(Long ownerId) {
        try {
            boolean exists = userFeignClient.existsAndIsOwner(ownerId);
            if (!exists) {
                throw new IllegalArgumentException(ExceptionMessages.USER_NOT_OWNER);
            }
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException(ExceptionMessages.USER_NOT_FOUND);
        }
    }
    @Override
    public String getPhoneByUserId(Long id) {
        return userFeignClient.getPhone(id);
    }



}
