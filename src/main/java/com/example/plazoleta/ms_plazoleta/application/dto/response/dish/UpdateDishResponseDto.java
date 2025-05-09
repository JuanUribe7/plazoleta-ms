package com.example.plazoleta.ms_plazoleta.application.dto.response.dish;

public record UpdateDishResponseDto (
     Long id,
     String name,
     Integer price,
     String description
){}