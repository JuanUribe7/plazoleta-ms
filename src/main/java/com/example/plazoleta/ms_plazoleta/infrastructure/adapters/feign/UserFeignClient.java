package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.feign;

import com.example.plazoleta.ms_plazoleta.infrastructure.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "usuarios-service", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface UserFeignClient {

    @GetMapping("/users/{id}/exists")
    boolean existsAndIsOwner(@PathVariable Long id);

    @GetMapping("/users/{id}/phone")
    String getPhone(@PathVariable Long id);

}