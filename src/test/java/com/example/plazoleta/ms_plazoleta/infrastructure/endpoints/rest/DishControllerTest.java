package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.UpdateDishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.DishServiceHandler;
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
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DishController.class)
class DishControllerTest {

    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper mapper;
    @MockBean private DishServiceHandler service;
    @MockBean private JwtUtil jwt;

    @Test
    @WithMockUser(roles="OWNER")
    void createDish_ok() throws Exception {
        DishRequestDto dto=new DishRequestDto();
        UpdateDishRequestDto unused=null;
        DishResponseDto resp=new DishResponseDto();
        resp.setId(5L);
        resp.setName("Taco");
        resp.setPrice(3000);

        when(jwt.extractUserId("tok")).thenReturn(7L);
        when(service.createDish(any(DishRequestDto.class))).thenReturn(resp);

        mvc.perform(post("/restaurants/3/dishes")
                        .with(csrf())
                        .header("Authorization","Bearer tok")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("Taco"))
                .andExpect(jsonPath("$.price").value(3000));
    }

    @Test
    @WithMockUser(roles="OWNER")
    void updateDish_ok() throws Exception {
        UpdateDishRequestDto dto=new UpdateDishRequestDto();
        UpdateDishResponseDto resp=new UpdateDishResponseDto();
        resp.setId(6L);
        resp.setName("Burrito");
        resp.setPrice(4000);
        resp.setDescription("Rico");

        when(jwt.extractUserId("tok")).thenReturn(7L);
        when(service.updateDish(any(UpdateDishRequestDto.class),any(Long.class))).thenReturn(resp);

        mvc.perform(patch("/restaurants/3/dishes/9")
                        .with(csrf())
                        .header("Authorization","Bearer tok")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.name").value("Burrito"))
                .andExpect(jsonPath("$.price").value(4000))
                .andExpect(jsonPath("$.description").value("Rico"));
    }

    @Test
    void unauthorized() throws Exception {
        mvc.perform(post("/restaurants/1/dishes")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }
}