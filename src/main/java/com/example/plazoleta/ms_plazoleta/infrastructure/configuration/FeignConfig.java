package com.example.plazoleta.ms_plazoleta.infrastructure.configuration;


import com.example.plazoleta.ms_plazoleta.infrastructure.client.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
