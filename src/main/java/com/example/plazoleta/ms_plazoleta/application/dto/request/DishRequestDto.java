package com.example.plazoleta.ms_plazoleta.application.dto.request;


import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishRequestDto {

    @NotBlank
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private CategoryType category;
    private Long restaurantId;

}