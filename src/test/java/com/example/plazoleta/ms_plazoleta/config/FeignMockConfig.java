package com.example.plazoleta.ms_plazoleta.config;

import com.example.plazoleta.ms_plazoleta.infrastructure.client.UserFeignClient;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class FeignMockConfig {

    @PostConstruct
    public void init() {
        System.out.println("🧪 FeignMockConfig cargado");
    }

    @Bean
    public UserFeignClient userFeignClient() {


        UserFeignClient mock = mock(UserFeignClient.class);
        when(mock.obtenerRolPorUsuario(1L)).thenReturn("OWNER");
        return mock;
    }
}