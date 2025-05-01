package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.usecases.create.CreateDishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish.DishValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateDishUseCaseTest {

    private DishPersistencePort dishPersistencePort;
    private RestaurantPersistencePort restaurantPersistencePort;
    private CreateDishUseCase useCase;
    private Dish dish;

    @BeforeEach
    void setUp() {
        dishPersistencePort = mock(DishPersistencePort.class);
        restaurantPersistencePort = mock(RestaurantPersistencePort.class);
        useCase = new CreateDishUseCase(dishPersistencePort, restaurantPersistencePort);
        dish = new Dish();
        dish.setName("TempDish");
        dish.setDescription("Valid description");
        dish.setCategory("Entradas");
        dish.setImageUrl("https://img.png");
        dish.setPrice(100);
        dish.setRestaurantId(1L);
        dish.setOwnerId(2L);
    }

    @Test
    void createDish_success() {
        try (MockedStatic<DishValidator> dv = mockStatic(DishValidator.class)) {
            dv.when(() -> DishValidator.validateDish(dish)).thenAnswer(i -> null);

            Restaurant r = new Restaurant();
            r.setId(1L);
            r.setOwnerId(2L);
            when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(r));
            when(dishPersistencePort.findByName("TempDish")).thenReturn(Optional.empty());
            when(dishPersistencePort.saveDish(dish)).thenReturn(dish);

            Dish result = useCase.createDish(dish);

            // Usamos getActive() en vez de isActive()
            assertTrue(result.getActive());
            verify(dishPersistencePort).saveDish(dish);
        }
    }

    @Test
    void createDish_restaurantNotFound() {
        try (MockedStatic<DishValidator> dv = mockStatic(DishValidator.class)) {
            dv.when(() -> DishValidator.validateDish(dish)).thenAnswer(i -> null);
            when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.empty());

            IllegalArgumentException ex =
                    assertThrows(IllegalArgumentException.class, () -> useCase.createDish(dish));
            assertEquals("El restaurante no existe", ex.getMessage());
        }
    }

    @Test
    void createDish_notOwner() {
        try (MockedStatic<DishValidator> dv = mockStatic(DishValidator.class)) {
            dv.when(() -> DishValidator.validateDish(dish)).thenAnswer(i -> null);

            Restaurant r = new Restaurant();
            r.setId(1L);
            r.setOwnerId(99L);
            when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(r));

            IllegalArgumentException ex =
                    assertThrows(IllegalArgumentException.class, () -> useCase.createDish(dish));
            assertEquals("No es propietario del restaurante.", ex.getMessage());
        }
    }

    @Test
    void createDish_duplicateName() {
        try (MockedStatic<DishValidator> dv = mockStatic(DishValidator.class)) {
            dv.when(() -> DishValidator.validateDish(dish)).thenAnswer(i -> null);

            Restaurant r = new Restaurant();
            r.setId(1L);
            r.setOwnerId(2L);
            when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(r));
            when(dishPersistencePort.findByName("TempDish"))
                    .thenReturn(Optional.of(new Dish()));

            IllegalArgumentException ex =
                    assertThrows(IllegalArgumentException.class, () -> useCase.createDish(dish));
            assertEquals("El nombre del plato ya está registrado.", ex.getMessage());
        }
    }

    @Test
    void createDish_emptyName() {
        try (MockedStatic<DishValidator> dv = mockStatic(DishValidator.class)) {
            dv.when(() -> DishValidator.validateDish(dish)).thenAnswer(i -> null);

            dish.setName("   ");
            Restaurant r = new Restaurant();
            r.setId(1L);
            r.setOwnerId(2L);
            when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(r));
            when(dishPersistencePort.findByName("   ")).thenReturn(Optional.empty());

            IllegalArgumentException ex =
                    assertThrows(IllegalArgumentException.class, () -> useCase.createDish(dish));
            assertEquals("El nombre no puede estar vacío", ex.getMessage());
        }
    }
}
