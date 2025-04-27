package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.IllegalLogoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishUseCaseTest {

    private IDishPersistencePort dishPersistencePort;
    private IRestaurantPersistencePort restaurantPersistencePort;
    private DishUseCase dishUseCase;

    @BeforeEach
    void setUp() {
        dishPersistencePort = mock(IDishPersistencePort.class);
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        dishUseCase = new DishUseCase(dishPersistencePort, restaurantPersistencePort);
    }

    @Test
    void createDish_successful() {
        Dish dish = validDish();
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(2L);

        when(restaurantPersistencePort.findById(dish.getRestaurantId())).thenReturn(Optional.of(restaurant));
        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.empty());
        when(dishPersistencePort.saveDish(any(Dish.class))).thenReturn(dish);

        Dish result = dishUseCase.createDish(dish);
        assertNotNull(result);
        assertTrue(result.getActive());
        verify(dishPersistencePort).saveDish(dish);
    }

    @Test
    void createDish_restaurantNotFound() {
        Dish dish = validDish();
        when(restaurantPersistencePort.findById(dish.getRestaurantId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("El restaurante no existe", exception.getMessage());
    }

    @Test
    void createDish_notOwnerOfRestaurant() {
        Dish dish = validDish();
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(99L); // diferente propietario

        when(restaurantPersistencePort.findById(dish.getRestaurantId())).thenReturn(Optional.of(restaurant));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("No es propietario del restaurante.", exception.getMessage());
    }

    @Test
    void createDish_nameAlreadyExists() {
        Dish dish = validDish();
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(2L);

        when(restaurantPersistencePort.findById(dish.getRestaurantId())).thenReturn(Optional.of(restaurant));
        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.of(dish));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("El nombre del plato ya está registrado.", exception.getMessage());
    }

    @Test
    void createDish_invalidImageUrl() {
        Dish dish = validDish();
        dish.setImageUrl(""); // inválida
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(2L);

        when(restaurantPersistencePort.findById(dish.getRestaurantId())).thenReturn(Optional.of(restaurant));
        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalLogoException.class, () -> dishUseCase.createDish(dish));
        assertEquals("La URL del logo no puede estar vacía", exception.getMessage());
    }
    @Test
    void createDish_invalidLocalUrl_shouldThrowException() {
        Dish dish = validDish();
        dish.setImageUrl("http://localhost/image.png");

        mockRestaurantOwner(dish);

        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("La URL del logo no puede ser local", exception.getMessage());
    }

    @Test
    void createDish_invalidUrlPattern_shouldThrowException() {
        Dish dish = validDish();
        dish.setImageUrl("ftp://invalid.com/image.png");

        mockRestaurantOwner(dish);

        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("La URL del logo debe ser válida y comenzar con http o https", exception.getMessage());
    }

    @Test
    void createDish_invalidImageExtension_shouldThrowException() {
        Dish dish = validDish();
        dish.setImageUrl("http://image.com/logo.bmp");

        mockRestaurantOwner(dish);

        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("La URL del logo debe terminar en .png, .jpg, .jpeg o .svg", exception.getMessage());
    }

    @Test
    void createDish_invalidCategoryPattern_shouldThrowException() {
        Dish dish = validDish();
        dish.setCategory("Fast123");

        mockRestaurantOwner(dish);

        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("La categoría solo puede contener letras y espacios", exception.getMessage());
    }

    @Test
    void createDish_invalidCategoryLength_shouldThrowException() {
        Dish dish = validDish();
        dish.setCategory("AB");

        mockRestaurantOwner(dish);

        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("La categoría debe tener entre 3 y 30 caracteres", exception.getMessage());
    }

    @Test
    void createDish_invalidDescriptionPattern_shouldThrowException() {
        Dish dish = validDish();
        dish.setDescription("Descripción inválida!!!###");

        mockRestaurantOwner(dish);

        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("La descripción solo puede contener letras, números, espacios, puntos y comas", exception.getMessage());
    }

    @Test
    void createDish_invalidDescriptionLength_shouldThrowException() {
        Dish dish = validDish();
        dish.setDescription("Corta");

        mockRestaurantOwner(dish);

        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("La descripción debe tener entre 10 y 200 caracteres", exception.getMessage());
    }

    // Helpers:
    private void mockRestaurantOwner(Dish dish) {
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(dish.getOwnerId());
        when(restaurantPersistencePort.findById(dish.getRestaurantId())).thenReturn(Optional.of(restaurant));
    }

    @Test
    void createDish_nameIsNull_shouldThrowException() {
        Dish dish = validDish();
        dish.setName(null);

        mockRestaurantOwner(dish);
        when(dishPersistencePort.findByName(null)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("El nombre no puede estar vacío", exception.getMessage());
    }

    @Test
    void createDish_categoryIsNull_shouldThrowException() {
        Dish dish = validDish();
        dish.setCategory(null);

        mockRestaurantOwner(dish);
        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("La categoría no puede estar vacía", exception.getMessage());
    }

    @Test
    void createDish_descriptionIsNull_shouldThrowException() {
        Dish dish = validDish();
        dish.setDescription(null);

        mockRestaurantOwner(dish);
        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("La descripción no puede estar vacía", exception.getMessage());
    }

    @Test
    void createDish_priceIsZero_shouldThrowException() {
        Dish dish = validDish();
        dish.setPrice(0);

        mockRestaurantOwner(dish);
        when(dishPersistencePort.findByName(dish.getName())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(dish));
        assertEquals("Precio debe ser mayor a 0", exception.getMessage());
    }

    // Puedes agregar más tests para cada validación de categoría, descripción, precio...

    private Dish validDish() {
        return new Dish(null, "Pizza", 15000, "Descripción válida 123", "http://image.com/logo.png", "Comida", 1L, 2L, false);
    }
}
