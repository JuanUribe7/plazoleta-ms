package com.example.plazoleta.ms_plazoleta.application.dto.response.dish;

public record DishResponseDto(
    Long id,
    String name,
    Integer price,
    String description,
    String imageUrl,
    String category
) {}
