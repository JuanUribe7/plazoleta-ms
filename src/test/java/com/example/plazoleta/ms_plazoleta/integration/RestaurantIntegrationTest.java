package com.example.plazoleta.ms_plazoleta.integration;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenCreateRestaurant_thenRestaurantIsPersisted() throws Exception {
        RestaurantRequestDto dto = new RestaurantRequestDto();
        dto.setName("Restaurante Integracion");
        dto.setNit("123456789");
        dto.setAddress("Calle 123 #45-67");
        dto.setPhone("+573001234567");
        dto.setUrlLogo("http://cdn.example.com/logo.png");
        dto.setOwnerId(5L);  // Asegúrate que Feign mock devuelve OWNER para 5L

        mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk()) // Cambia a .isCreated() si tu endpoint devuelve 201
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Restaurante Integracion"))
                .andExpect(jsonPath("$.address").value("Calle 123 #45-67"));

        Optional<?> savedRestaurant = restaurantRepository.findByNit("123456789");
        assertTrue(savedRestaurant.isPresent());
    }
}