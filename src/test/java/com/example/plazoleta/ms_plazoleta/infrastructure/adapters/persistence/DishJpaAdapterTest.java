package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.DishEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.DishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DishJpaAdapterTest {
    @Mock private DishRepository repo;
    @Mock private DishEntityMapper mapper;
    @InjectMocks private DishJpaAdapter adapter;
    private Dish model;
    private DishEntity entity;

    @BeforeEach void init() {
        model = new Dish();
        model.setId(1L);
        model.setName("Name");
        entity = mock(DishEntity.class);
    }

    @Test void saveDish_returnsMappedModel() {
        when(mapper.toEntity(model)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.toModel(entity)).thenReturn(model);
        assertSame(model, adapter.saveDish(model));
    }

    @Test void updateDish_returnsMappedModel() {
        when(mapper.toEntity(model)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.toModel(entity)).thenReturn(model);
        assertSame(model, adapter.updateDish(model));
    }

    @Test void findByName_present() {
        when(repo.findByName("Name")).thenReturn(Optional.of(entity));
        when(mapper.toModel(entity)).thenReturn(model);
        Optional<Dish> res = adapter.findByName("Name");
        assertTrue(res.isPresent());
        assertSame(model, res.get());
    }

    @Test void findByName_empty() {
        when(repo.findByName("X")).thenReturn(Optional.empty());
        assertTrue(adapter.findByName("X").isEmpty());
    }

    @Test void findById_present() {
        when(repo.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toModel(entity)).thenReturn(model);
        Optional<Dish> res = adapter.findById(1L);
        assertTrue(res.isPresent());
        assertSame(model, res.get());
    }

    @Test void findById_empty() {
        when(repo.findById(2L)).thenReturn(Optional.empty());
        assertTrue(adapter.findById(2L).isEmpty());
    }
}
