package com.example.plazoleta.ms_plazoleta.application.mappers;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DishDtoMapperTest {


    @Autowired
    private DishDtoMapper mapper;

    @Test
    void testToModel() {
        DishRequestDto dto = new DishRequestDto("Pasta", 12000, "Italian dish", "http://image.com/pasta.png", "Main Course", 1L, 2L);

        Dish dish = mapper.toModel(dto);

        assertEquals(dto.getName(), dish.getName());
        assertEquals(dto.getPrice(), dish.getPrice());
        assertEquals(dto.getDescription(), dish.getDescription());
        assertEquals(dto.getImageUrl(), dish.getImageUrl());
        assertEquals(dto.getCategory(), dish.getCategory());
        assertEquals(dto.getRestaurantId(), dish.getRestaurantId());
        assertEquals(dto.getOwnerId(), dish.getOwnerId());
    }

    @Test
    void testToResponseDto() {
        Dish dish = new Dish(1L, "Pizza", 15000, "Cheese Pizza", "http://image.com/pizza.png", "Fast Food", 1L, 2L, true);

        DishResponseDto dto = mapper.toResponseDto(dish);

        assertNotNull(dto);
        assertEquals(dish.getId(), dto.getId());
        assertEquals(dish.getName(), dto.getName());
        assertEquals(dish.getPrice(), dto.getPrice());
        assertEquals(dish.getDescription(), dto.getDescription());
        assertEquals(dish.getImageUrl(), dto.getImageUrl());
        assertEquals(dish.getCategory(), dto.getCategory());
        assertEquals(dish.getRestaurantId(), dto.getRestaurantId());
        assertEquals(dish.getActive(), dto.getActive());
    }
}