package com.example.plazoleta.ms_plazoleta.infrastructure.mappers;

import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class DishEntityMapperTest {

    private final DishEntityMapper mapper = Mappers.getMapper(DishEntityMapper.class);

    @Test
    void testToModel() {
        DishEntity entity = new DishEntity(1L, "Pizza", 12000, "Delicious pizza", "Fast Food", "http://image.com/pizza.png", 2L, true, 3L);

        Dish model = mapper.toModel(entity);

        assertNotNull(model);
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getName(), model.getName());
        assertEquals(entity.getPrice(), model.getPrice());
        assertEquals(entity.getDescription(), model.getDescription());
        assertEquals(entity.getCategory(), model.getCategory());
        assertEquals(entity.getImageUrl(), model.getImageUrl());
        assertEquals(entity.getRestaurantId(), model.getRestaurantId());
        assertEquals(entity.getActive(), model.getActive());
        assertEquals(entity.getOwnerId(), model.getOwnerId());
    }

    @Test
    void testToEntity() {
        Dish model = new Dish(1L, "Burger", 15000, "Tasty burger", "http://image.com/burger.png", "Main Course", 4L, 5L, false);

        DishEntity entity = mapper.toEntity(model);

        assertNotNull(entity);
        assertEquals(model.getId(), entity.getId());
        assertEquals(model.getName(), entity.getName());
        assertEquals(model.getPrice(), entity.getPrice());
        assertEquals(model.getDescription(), entity.getDescription());
        assertEquals(model.getCategory(), entity.getCategory());
        assertEquals(model.getImageUrl(), entity.getImageUrl());
        assertEquals(model.getRestaurantId(), entity.getRestaurantId());
        assertEquals(model.getActive(), entity.getActive());
        assertEquals(model.getOwnerId(), entity.getOwnerId());
    }
}
