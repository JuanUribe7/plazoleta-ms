
package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantServiceHandler;
import com.example.plazoleta.ms_plazoleta.infrastructure.security.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {

    @Mock
    private RestaurantServiceHandler restaurantServiceHandler;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private RestaurantController restaurantController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    @Test
    void createRestaurant_success() throws Exception {
        RestaurantRequestDto dto = new RestaurantRequestDto();
        dto.setName("New Restaurant");

        RestaurantResponseDto responseDto = new RestaurantResponseDto();
        responseDto.setName("New Restaurant");

        when(jwtUtil.extractUserId("valid.token")).thenReturn(1L);
        when(restaurantServiceHandler.createRestaurant(any(RestaurantRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", "Bearer valid.token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Restaurant"));
    }

    @Test
    void isOwnerOfRestaurant_true() throws Exception {
        when(restaurantServiceHandler.isOwnerOfRestaurant(5L, 1L)).thenReturn(true);

        mockMvc.perform(get("/restaurants/5/owner/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void isOwnerOfRestaurant_false() throws Exception {
        when(restaurantServiceHandler.isOwnerOfRestaurant(5L, 1L)).thenReturn(false);

        mockMvc.perform(get("/restaurants/5/owner/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
