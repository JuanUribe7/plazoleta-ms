package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.DishEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.DishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishJpaAdapterTest {

    private DishRepository dishRepository;
    private DishEntityMapper mapper;
    private DishJpaAdapter adapter;

    @BeforeEach
    void setUp() {
        dishRepository = mock(DishRepository.class);
        mapper = mock(DishEntityMapper.class);
        adapter = new DishJpaAdapter(dishRepository, mapper);
    }

    @Test
    void testSaveDish() {
        Dish dish = new Dish();
        DishEntity entity = new DishEntity();
        Dish savedDish = new Dish();

        when(mapper.toEntity(dish)).thenReturn(entity);
        when(dishRepository.save(entity)).thenReturn(entity);
        when(mapper.toModel(entity)).thenReturn(savedDish);

        Dish result = adapter.saveDish(dish);

        assertEquals(savedDish, result);
        verify(mapper).toEntity(dish);
        verify(dishRepository).save(entity);
        verify(mapper).toModel(entity);
    }

    @Test
    void testFindByName_Found() {
        String name = "Pizza";
        DishEntity entity = new DishEntity();
        Dish dish = new Dish();

        when(dishRepository.findByName(name)).thenReturn(Optional.of(entity));
        when(mapper.toModel(entity)).thenReturn(dish);

        Optional<Dish> result = adapter.findByName(name);

        assertTrue(result.isPresent());
        assertEquals(dish, result.get());
        verify(dishRepository).findByName(name);
        verify(mapper).toModel(entity);
    }

    @Test
    void testFindByName_NotFound() {
        String name = "Sushi";

        when(dishRepository.findByName(name)).thenReturn(Optional.empty());

        Optional<Dish> result = adapter.findByName(name);

        assertFalse(result.isPresent());
        verify(dishRepository).findByName(name);
        verify(mapper, never()).toModel(any());
    }
}
