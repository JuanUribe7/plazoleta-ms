package com.example.plazoleta.ms_plazoleta.infrastructure.mappers;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantEntityMapperTest {

    private RestaurantEntityMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(RestaurantEntityMapper.class);
    }

    @Test
    void testToModel() {
        RestaurantEntity entity = new RestaurantEntity(1L, "Rest", "12345678", "calle 77#21Asur-05", "+3103479455", "http://foto.png", 1L);
        Restaurant model = mapper.toModel(entity);

        assertNotNull(model);
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getName(), model.getName());
        assertEquals(entity.getNit(), model.getNit());
        assertEquals(entity.getAddress(), model.getAddress());
        assertEquals(entity.getPhone(), model.getPhone());
        assertEquals(entity.getUrlLogo(), model.getUrlLogo());
        assertEquals(entity.getOwnerId(), model.getOwnerId());
    }

    @Test
    void testToEntity() {
        Restaurant model = new Restaurant(1L, "Rest", "12345678", "calle 77#21Asur-05", "+3103479455", "http://foto.png", 1L);
        RestaurantEntity entity = mapper.toEntity(model);

        assertNotNull(entity);
        assertEquals(model.getId(), entity.getId());
        assertEquals(model.getName(), entity.getName());
        assertEquals(model.getNit(), entity.getNit());
        assertEquals(model.getAddress(), entity.getAddress());
        assertEquals(model.getPhone(), entity.getPhone());
        assertEquals(model.getUrlLogo(), entity.getUrlLogo());
        assertEquals(model.getOwnerId(), entity.getOwnerId());
    }

    @Test
    void testToModelList() {
        RestaurantEntity entity1 = new RestaurantEntity(1L, "Rest1", "11111111", "calle 1", "+3100000001", "http://foto1.png", 1L);
        RestaurantEntity entity2 = new RestaurantEntity(2L, "Rest2", "22222222", "calle 2", "+3100000002", "http://foto2.png", 2L);
        List<RestaurantEntity> entityList = Arrays.asList(entity1, entity2);

        List<Restaurant> modelList = mapper.toModelList(entityList);

        assertNotNull(modelList);
        assertEquals(2, modelList.size());
        assertEquals("Rest1", modelList.get(0).getName());
        assertEquals("Rest2", modelList.get(1).getName());
    }
}
