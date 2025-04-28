package com.example.plazoleta.ms_plazoleta.integration;


import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.infrastructure.client.UserFeignClient;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestaurantIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFeignClient userFeignClient;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        restaurantRepository.deleteAll(); // Limpiar BD H2 antes de cada prueba
    }

    @Test
    void shouldCreateRestaurantSuccessfully() throws Exception {
        // Preparar datos válidos
        RestaurantRequestDto requestDto = new RestaurantRequestDto(
                "Restaurante Uno",
                "12345678",
                "Calle 123",
                "+1234567890",
                "http://logo.com/image.png",
                1L
        );

        // Simular respuesta de FeignClient
        when(userFeignClient.getRoleByUser(1L)).thenReturn("OWNER");

        // Ejecutar solicitud POST
        mockMvc.perform(post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Restaurante Uno"))
                .andExpect(jsonPath("$.address").value("Calle 123"))
                .andExpect(jsonPath("$.urlLogo").value("http://logo.com/image.png"));
    }
}