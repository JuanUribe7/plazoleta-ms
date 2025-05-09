package com.example.plazoleta.ms_plazoleta.application.dto.response.dish;


import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;

public record DishSimpleResponseDto(
         Long id,
         String name,
         Integer price,
         String description,
         String imageUrl,
         CategoryType category
){}


