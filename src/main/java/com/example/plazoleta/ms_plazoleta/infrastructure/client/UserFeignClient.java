package com.example.plazoleta.ms_plazoleta.infrastructure.client;


import com.example.plazoleta.ms_plazoleta.infrastructure.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "usuarios-service", url = "http://localhost:8086", configuration = FeignConfig.class)
public interface UserFeignClient {

    @GetMapping("/user/{id}/rol")
    String getRoleByUser(@PathVariable Long id);
}