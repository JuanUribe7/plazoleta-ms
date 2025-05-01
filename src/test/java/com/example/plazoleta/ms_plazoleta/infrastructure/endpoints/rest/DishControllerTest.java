package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;


import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.UpdateDishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.DishService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DishControllerTest {

    @Mock
    private DishService dishService;

    @Mock
    private JwtUtil jwtUtil;

    private MockMvc mockMvc;

    @InjectMocks
    private DishController dishController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(dishController).build();
    }

    @Test
    void createDish_success() throws Exception {
        DishRequestDto dto = new DishRequestDto();
        dto.setName("Dish Name");
        DishResponseDto responseDto = new DishResponseDto();
        responseDto.setName("Dish Name");

        when(jwtUtil.extractUserId("valid.token")).thenReturn(1L);
        when(dishService.createDish(any(DishRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/restaurants/5/dishes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", "Bearer valid.token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dish Name"));
    }

    @Test
    void changeDishStatus_success() throws Exception {
        when(jwtUtil.extractUserId("valid.token")).thenReturn(1L);

        mockMvc.perform(patch("/restaurants/5/dishes/10/status")
                        .param("active", "true")
                        .header("Authorization", "Bearer valid.token"))
                .andExpect(status().isNoContent());

        verify(dishService).changeDishStatus(10L, 5L, 1L, true);
    }

    @Test
    void updateDish_success() throws Exception {
        UpdateDishRequestDto dto = new UpdateDishRequestDto();


        UpdateDishResponseDto responseDto = new UpdateDishResponseDto();
        responseDto.setName("Updated Dish");

        when(jwtUtil.extractUserId("valid.token")).thenReturn(1L);
        when(dishService.updateDish(any(UpdateDishRequestDto.class), eq(10L))).thenReturn(responseDto);

        mockMvc.perform(patch("/restaurants/5/dishes/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", "Bearer valid.token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Dish"));
    }
}