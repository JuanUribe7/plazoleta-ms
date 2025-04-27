package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.DishDtoMapper;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IDishServicePort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishServiceImplTest {

    @Test
    void createDish_shouldConvertAndPersistDishCorrectly() {
        DishDtoMapper mapper = mock(DishDtoMapper.class);
        IDishServicePort dishServicePort = mock(IDishServicePort.class);

        DishRequestDto dto = new DishRequestDto("Dish Name", 15000, "Description", "http://image.url", "Main", 1L, 5L);
        Dish dish = new Dish(1L, "Dish Name", 15000, "Description", "http://image.url", "Main", 1L, 5L, true);
        Dish savedDish = new Dish(1L, "Dish Name", 15000, "Description", "http://image.url", "Main", 1L, 5L, true);
        DishResponseDto responseDto = new DishResponseDto(1L, "Dish Name", 15000, "Description", "http://image.url", "Main", 1L, true);

        when(mapper.toModel(dto)).thenReturn(dish);
        when(dishServicePort.createDish(dish)).thenReturn(savedDish);
        when(mapper.toResponseDto(savedDish)).thenReturn(responseDto);

        DishServiceImpl service = new DishServiceImpl(dishServicePort, mapper);
        DishResponseDto result = service.createDish(1L, dto, 5L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Dish Name", result.getName());
        assertEquals(15000, result.getPrice());
        verify(mapper).toModel(dto);
        verify(dishServicePort).createDish(dish);
        verify(mapper).toResponseDto(savedDish);
    }
}
