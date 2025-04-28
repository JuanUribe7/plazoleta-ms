
package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantServiceHandler;
import com.example.plazoleta.ms_plazoleta.infrastructure.security.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private RestaurantServiceHandler restaurantServiceHandler;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    @WithMockUser(roles = "ADMIN")
    void createRestaurant_withValidToken_returnsOkAndBody() throws Exception {
        RestaurantRequestDto requestDto = new RestaurantRequestDto();
        RestaurantResponseDto resp = new RestaurantResponseDto();
        resp.setId(1L);
        resp.setName("MyRestaurant");

        when(jwtUtil.extractUserId("token123")).thenReturn(1L);
        when(restaurantServiceHandler.createRestaurant(any(RestaurantRequestDto.class))).thenReturn(resp);

        mockMvc.perform(post("/restaurants")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer token123")
                        .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("MyRestaurant"));
    }
    @Test
    void createRestaurant_withoutAuthentication_returnsUnauthorized() throws Exception {
        mockMvc.perform(post("/restaurants")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }
}