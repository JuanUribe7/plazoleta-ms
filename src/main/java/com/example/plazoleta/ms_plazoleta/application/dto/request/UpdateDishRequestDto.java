package com.example.plazoleta.ms_plazoleta.application.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDishRequestDto {

    private Long dishId;
    private Integer price;
    private String description;
    private Long restaurantId;

    }

