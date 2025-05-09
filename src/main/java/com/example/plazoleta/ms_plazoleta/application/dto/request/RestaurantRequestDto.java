package com.example.plazoleta.ms_plazoleta.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RestaurantRequestDto(
        @NotBlank
         String name,
         String nit,
         String address,
         String phone,
         String urlLogo,
         Long ownerId
){}
