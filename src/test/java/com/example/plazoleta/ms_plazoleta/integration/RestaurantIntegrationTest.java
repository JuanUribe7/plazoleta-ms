package com.example.plazoleta.ms_plazoleta.integration;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.infrastructure.client.UserFeignClient;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Key;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RestaurantIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @MockBean
    private UserFeignClient userFeignClient;

    @Value("${jwt.secret-key}")
    private String secretKey;

    private String adminToken;

    @BeforeEach
    void setUp() {
        restaurantRepository.deleteAll();

        when(userFeignClient.getRoleByUser(1L)).thenReturn("OWNER");

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        adminToken = Jwts.builder()
                .setSubject("user")
                .claim("userId", 1L)
                .claim("role", "ADMIN")
                .setIssuedAt(new Date())
                .signWith(key)
                .compact();
    }

    @Test
    void shouldCreateRestaurantSuccessfully() throws Exception {
        RestaurantRequestDto requestDto = new RestaurantRequestDto(
                "Restaurante Uno",
                "12345678",
                "Calle 123",
                "+1234567890",
                "http://logo.com/image.png",
                1L
        );

        mockMvc.perform(post("/restaurants")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Restaurante Uno"))
                .andExpect(jsonPath("$.address").value("Calle 123"))
                .andExpect(jsonPath("$.urlLogo").value("http://logo.com/image.png"));
    }
}
