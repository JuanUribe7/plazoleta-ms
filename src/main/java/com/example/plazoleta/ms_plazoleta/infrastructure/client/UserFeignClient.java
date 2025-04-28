package com.example.plazoleta.ms_plazoleta.infrastructure.client;

import com.example.plazoleta.ms_plazoleta.infrastructure.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(name = "usuarios-service", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface UserFeignClient {

    @GetMapping("/users/{id}/rol")
    String getRoleByUser(@PathVariable Long id);

    @PutMapping("/users/{ownerId}/restaurant/{restaurantId}")
    void updateOwnerRestaurant(@PathVariable Long ownerId, @PathVariable Long restaurantId);
}