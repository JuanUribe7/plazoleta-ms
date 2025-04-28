package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;



import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.UpdateDishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.DishServiceHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DishController.class)
class DishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private DishServiceHandler dishServiceHandler;

    private DishResponseDto sampleCreateResp;
    private UpdateDishResponseDto sampleUpdateResp;

    @BeforeEach
    void setUp() {
        // Para createDish
        sampleCreateResp = new DishResponseDto();
        sampleCreateResp.setId(10L);
        sampleCreateResp.setName("Taco");
        sampleCreateResp.setPrice(5000);

        // Para updateDish
        sampleUpdateResp = new UpdateDishResponseDto();
        sampleUpdateResp.setId(20L);
        sampleUpdateResp.setName("Burrito");
        sampleUpdateResp.setPrice(7000);
        sampleUpdateResp.setDescription("Sabroso burrito");
    }

    @Test
    void createDish_deberiaRetornar200yBody() throws Exception {
        Mockito.when(dishServiceHandler.createDish(any(DishRequestDto.class)))
                .thenReturn(sampleCreateResp);

        DishRequestDto req = new DishRequestDto();
        req.setName("Taco");
        req.setPrice(5000);

        mockMvc.perform(post("/restaurants/5/dishes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("ownerId", 42L)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.name").value("Taco"))
                .andExpect(jsonPath("$.price").value(5000));
    }

    @Test
    void updateDish_deberiaRetornar200yBody() throws Exception {
        Mockito.when(dishServiceHandler.updateDish(any(UpdateDishRequestDto.class), eq(7L)))
                .thenReturn(sampleUpdateResp);

        UpdateDishRequestDto req = new UpdateDishRequestDto();
        req.setDescription("Sabroso burrito");
        req.setPrice(7000);

        mockMvc.perform(patch("/restaurants/3/dishes/7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("ownerId", 99L)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(20))
                .andExpect(jsonPath("$.name").value("Burrito"))
                .andExpect(jsonPath("$.description").value("Sabroso burrito"))
                .andExpect(jsonPath("$.price").value(7000));
    }
}
