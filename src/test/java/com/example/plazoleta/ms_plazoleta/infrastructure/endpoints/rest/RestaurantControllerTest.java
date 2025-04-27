package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.DishService;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
@Import({RestaurantService.class, DishService.class})
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private DishService dishService;

    @Test
    void createRestaurant_success() throws Exception {
        RestaurantRequestDto requestDto = new RestaurantRequestDto("Restaurante", "123456", "Calle 1", "+573001112233", "http://logo.png", 1L);
        RestaurantResponseDto responseDto = new RestaurantResponseDto(1L, "Restaurante", "Calle 1", "http://logo.png");

        when(restaurantService.createRestaurant(any())).thenReturn(responseDto);

        mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Restaurante"));
    }

    @Test
    void createDish_success() throws Exception {
        DishRequestDto requestDto = new DishRequestDto(
                "Dish Name", 12000, "Delicious dish", "http://image.com/logo.png", "Main Course", 1L, 5L
        );
        DishResponseDto responseDto = new DishResponseDto(
                1L, "Dish Name", 12000, "Delicious dish", "http://image.com/logo.png", "Main Course", 1L, true
        );

        when(dishService.createDish(any(Long.class), any(DishRequestDto.class), any(Long.class))).thenReturn(responseDto);

        mockMvc.perform(post("/restaurant/1/dish")
                        .header("ownerId", "5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Dish Name"))
                .andExpect(jsonPath("$.price").value(12000))
                .andExpect(jsonPath("$.description").value("Delicious dish"))
                .andExpect(jsonPath("$.imageUrl").value("http://image.com/logo.png"))
                .andExpect(jsonPath("$.category").value("Main Course"))
                .andExpect(jsonPath("$.restaurantId").value(1))
                .andExpect(jsonPath("$.active").value(true));
    }

}