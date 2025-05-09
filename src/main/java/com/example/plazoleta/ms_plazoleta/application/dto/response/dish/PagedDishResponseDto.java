package com.example.plazoleta.ms_plazoleta.application.dto.response.dish;

import java.util.List;

public record PagedDishResponseDto(
         List<DishSimpleResponseDto> content,
         int totalPages,
         long totalElements,
         int size,
         int page,
         boolean isFirst,
         boolean isLast,
         boolean hasNext,
         boolean hasPrevious

){}
