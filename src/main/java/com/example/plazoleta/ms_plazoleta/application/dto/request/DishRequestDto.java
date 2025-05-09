package com.example.plazoleta.ms_plazoleta.application.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record DishRequestDto (
     @NotBlank
     String name,
     Integer price,
     String description,
     String imageUrl,
     String category,
     Long restaurantId
)
{}