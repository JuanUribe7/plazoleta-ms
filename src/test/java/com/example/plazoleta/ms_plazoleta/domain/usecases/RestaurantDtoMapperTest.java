
package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.RestaurantDtoMapper;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RestaurantDtoMapperTest {


    @Autowired
    private RestaurantDtoMapper restaurantDtoMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void shouldMapDtoToModel() {
        RestaurantRequestDto dto = new RestaurantRequestDto("Rest 1", "12345678", "Main St", "+573001112233", "http://logo.com/img.png", 1L);
        Restaurant restaurant = restaurantDtoMapper.toModel(dto);

        assertNotNull(restaurant);
        assertEquals(dto.getName(), restaurant.getName());
    }
}
