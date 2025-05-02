package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.dish.DishDtoMapper;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.CreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.UpdateDishServicePort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DishServiceImplTest {

    @Test
    void createDish_shouldConvertAndPersistDishCorrectly() {
        DishDtoMapper mapper = mock(DishDtoMapper.class);
        CreateDishServicePort createDishServicePort = mock(CreateDishServicePort.class);
        UpdateDishServicePort updateDishServicePort = mock(UpdateDishServicePort.class);

        DishRequestDto dto = new DishRequestDto("Dish Name", 15000, "Description", "http://image.url", "Main", 1L, 5L);
        Dish dish = new Dish(1L, "Dish Name", 15000, "Description", "http://image.url", "Main", 1L, 5L, true);
        Dish savedDish = new Dish(1L, "Dish Name", 15000, "Description", "http://image.url", "Main", 1L, 5L, true);
        DishResponseDto responseDto = new DishResponseDto(1L, "Dish Name", 15000, "Description", "http://image.url", "Main", 1L, true);

        when(mapper.toModel(dto)).thenReturn(dish);
        when(createDishServicePort.createDish(dish)).thenReturn(savedDish);
        when(mapper.toResponseDto(savedDish)).thenReturn(responseDto);

        DishServiceImpl service = new DishServiceImpl(createDishServicePort, mapper,updateDishServicePort );
        DishResponseDto result = service.createDish( dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Dish Name", result.getName());
        assertEquals(15000, result.getPrice());
        verify(mapper).toModel(dto);
        verify(createDishServicePort).createDish(dish);
        verify(mapper).toResponseDto(savedDish);
    }
}
