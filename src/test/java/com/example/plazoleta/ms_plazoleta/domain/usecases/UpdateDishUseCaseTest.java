package com.example.plazoleta.ms_plazoleta.domain.usecases;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UpdateDishUseCaseTest {
    @Mock private IDishPersistencePort dishPersistence;
    @Mock private IRestaurantPersistencePort restaurantPersistence;
    @InjectMocks private UpdateDishUseCase useCase;
    private Dish input, existing;
    @BeforeEach void setup() {
        input = new Dish();
        input.setRestaurantId(2L);
        input.setOwnerId(42L);
        input.setDescription("nueva descripcion");
        input.setPrice(200);
        existing = new Dish();
        existing.setName("OriginalName");
    }
    @Test void updateDish_successful() {
        when(dishPersistence.findById(1L)).thenReturn(Optional.of(existing));
        Restaurant rest = mock(Restaurant.class);
        when(restaurantPersistence.findById(2L)).thenReturn(Optional.of(rest));
        when(rest.getOwnerId()).thenReturn(42L);
        Dish returned = mock(Dish.class);
        when(dishPersistence.updateDish(any(Dish.class))).thenReturn(returned);
        Dish result = useCase.updateDish(input,1L);
        assertSame(returned,result);
        ArgumentCaptor<Dish> c = ArgumentCaptor.forClass(Dish.class);
        verify(dishPersistence).updateDish(c.capture());
        Dish passed = c.getValue();
        assertEquals(1L,passed.getId());
        assertEquals("OriginalName",passed.getName());
        assertEquals(2L,passed.getRestaurantId());
        assertEquals(42L,passed.getOwnerId());
    }
    @Test void updateDish_whenDishNotFound_throws() {
        when(dishPersistence.findById(5L)).thenReturn(Optional.empty());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->useCase.updateDish(input,5L));
        assertEquals("Plato no encontrado",ex.getMessage());
    }
    @Test void updateDish_whenRestaurantNotFound_throws() {
        when(dishPersistence.findById(1L)).thenReturn(Optional.of(existing));
        when(restaurantPersistence.findById(2L)).thenReturn(Optional.empty());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->useCase.updateDish(input,1L));
        assertEquals("Restaurante no encontrado",ex.getMessage());
    }
    @Test void updateDish_whenWrongOwner_throws() {
        when(dishPersistence.findById(1L)).thenReturn(Optional.of(existing));
        Restaurant rest2 = mock(Restaurant.class);
        when(restaurantPersistence.findById(2L)).thenReturn(Optional.of(rest2));
        when(rest2.getOwnerId()).thenReturn(99L);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->useCase.updateDish(input,1L));
        assertEquals("No eres el propietario de este restaurante.",ex.getMessage());
    }
}
