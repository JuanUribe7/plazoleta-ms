
package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.PagedRestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantService;
import com.example.plazoleta.ms_plazoleta.infrastructure.security.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

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
        when(restaurantService.createRestaurant(any(RestaurantRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", "Bearer valid.token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Restaurant"));
    }


    @Test
    void listRestaurants_ShouldReturnPagedResponse() {
        // Arrange
        List<RestaurantSimpleResponseDto> content = Arrays.asList(
                new RestaurantSimpleResponseDto(),
                new RestaurantSimpleResponseDto()
        );
        Page<RestaurantSimpleResponseDto> page = new PageImpl<>(
                content,
                PageRequest.of(1, 2),
                5
        );
        when(restaurantService.getAllRestaurantsPaged(1, 2)).thenReturn(page);

        // Act
        ResponseEntity<PagedRestaurantResponseDto> response = restaurantController.listRestaurants(1, 2);
        PagedRestaurantResponseDto body = response.getBody();

        // Assert
        assertNotNull(body);
        assertEquals(content, body.getRestaurants());
        assertEquals(page.getTotalPages(), body.getPagination().getTotalPages());
        assertEquals(page.getTotalElements(), body.getPagination().getTotalElements());
        verify(restaurantService).getAllRestaurantsPaged(1, 2);
    }

    @Test
    void isOwnerOfRestaurant_true() throws Exception {
        when(restaurantService.isOwnerOfRestaurant(5L, 1L)).thenReturn(true);

        mockMvc.perform(get("/restaurants/5/owner/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void isOwnerOfRestaurant_false() throws Exception {
        when(restaurantService.isOwnerOfRestaurant(5L, 1L)).thenReturn(false);

        mockMvc.perform(get("/restaurants/5/owner/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
