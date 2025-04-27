package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.RestaurantEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantJpaAdapterTest {

    private RestaurantRepository restaurantRepository;
    private RestaurantEntityMapper mapper;
    private RestaurantJpaAdapter adapter;

    @BeforeEach
    void setUp() {
        restaurantRepository = mock(RestaurantRepository.class);
        mapper = mock(RestaurantEntityMapper.class);
        adapter = new RestaurantJpaAdapter(restaurantRepository, mapper);
    }

    @Test
    void saveRestaurant_ShouldMapAndSave() {
        Restaurant domain = new Restaurant();
        RestaurantEntity entity = new RestaurantEntity();
        Restaurant mappedBack = new Restaurant();

        when(mapper.toEntity(domain)).thenReturn(entity);
        when(restaurantRepository.save(entity)).thenReturn(entity);
        when(mapper.toModel(entity)).thenReturn(mappedBack);

        Restaurant result = adapter.saveRestaurant(domain);

        assertEquals(mappedBack, result);
        verify(mapper).toEntity(domain);
        verify(restaurantRepository).save(entity);
        verify(mapper).toModel(entity);
    }

    @Test
    void findByNit_WhenExists() {
        RestaurantEntity entity = new RestaurantEntity();
        Restaurant domain = new Restaurant();
        String nit = "123456";

        when(restaurantRepository.findByNit(nit)).thenReturn(Optional.of(entity));
        when(mapper.toModel(entity)).thenReturn(domain);

        Optional<Restaurant> result = adapter.findByNit(nit);

        assertTrue(result.isPresent());
        assertEquals(domain, result.get());
    }

    @Test
    void findByNit_WhenNotExists() {
        when(restaurantRepository.findByNit("not-found")).thenReturn(Optional.empty());

        Optional<Restaurant> result = adapter.findByNit("not-found");

        assertFalse(result.isPresent());
    }

    @Test
    void findByName_WhenExists() {
        RestaurantEntity entity = new RestaurantEntity();
        Restaurant domain = new Restaurant();
        String name = "Pasta House";

        when(restaurantRepository.findByName(name)).thenReturn(Optional.of(entity));
        when(mapper.toModel(entity)).thenReturn(domain);

        Optional<Restaurant> result = adapter.findByName(name);

        assertTrue(result.isPresent());
        assertEquals(domain, result.get());
    }

    @Test
    void findByName_WhenNotExists() {
        when(restaurantRepository.findByName("Ghost Restaurant")).thenReturn(Optional.empty());

        Optional<Restaurant> result = adapter.findByName("Ghost Restaurant");

        assertFalse(result.isPresent());
    }

    @Test
    void findById_WhenExists() {
        RestaurantEntity entity = new RestaurantEntity();
        Restaurant domain = new Restaurant();
        Long id = 42L;

        when(restaurantRepository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toModel(entity)).thenReturn(domain);

        Optional<Restaurant> result = adapter.findById(id);

        assertTrue(result.isPresent());
        assertEquals(domain, result.get());
    }

    @Test
    void findById_WhenNotExists() {
        when(restaurantRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Restaurant> result = adapter.findById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void findByUrlLogo_WhenExists() {
        RestaurantEntity entity = new RestaurantEntity();
        Restaurant domain = new Restaurant();
        String logo = "http://logo.com/image.png";

        when(restaurantRepository.findByUrlLogo(logo)).thenReturn(Optional.of(entity));
        when(mapper.toModel(entity)).thenReturn(domain);

        Optional<Restaurant> result = adapter.findByUrlLogo(logo);

        assertTrue(result.isPresent());
        assertEquals(domain, result.get());
    }

    @Test
    void findByUrlLogo_WhenNotExists() {
        when(restaurantRepository.findByUrlLogo("http://logo.com/missing.png")).thenReturn(Optional.empty());

        Optional<Restaurant> result = adapter.findByUrlLogo("http://logo.com/missing.png");

        assertFalse(result.isPresent());
    }
}
