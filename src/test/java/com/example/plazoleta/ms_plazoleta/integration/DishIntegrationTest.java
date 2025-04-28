package com.example.plazoleta.ms_plazoleta.integration;



import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class DishIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private ObjectMapper objectMapper;

    @Value("${jwt.secret-key}")
    private String secretKey;

    private Long restaurantId;
    private String jwtToken;

    @BeforeEach
    void setUp() {

        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName("Restaurante Uno");
        restaurant.setNit("123456789");
        restaurant.setAddress("Calle 123");
        restaurant.setPhone("+1234567890");
        restaurant.setUrlLogo("http://logo.com/image.png");
        restaurant.setOwnerId(1L);
        restaurant = restaurantRepository.save(restaurant);
        restaurantId = restaurant.getId();

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        jwtToken = Jwts.builder()
                .setSubject("test-user")
                .claim("userId", 1L)
                .claim("role", "OWNER")
                .signWith(key)
                .compact();
    }

    @Test
    void shouldCreateDishSuccessfully() throws Exception {

        DishRequestDto dto = new DishRequestDto();
        dto.setName("Dish Uno");
        dto.setPrice(15000);
        dto.setDescription("Plato delicioso de prueba");
        dto.setImageUrl("http://image.com/plato.png");
        dto.setCategory("Entradas");
        String json = objectMapper.writeValueAsString(dto);


        mockMvc.perform(post("/restaurants/" + restaurantId + "/dishes")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dish Uno"))
                .andExpect(jsonPath("$.price").value(15000))
                .andExpect(jsonPath("$.description").value("Plato delicioso de prueba"))
                .andExpect(jsonPath("$.imageUrl").value("http://image.com/plato.png"))
                .andExpect(jsonPath("$.category").value("Entradas"))
                .andExpect(jsonPath("$.restaurantId").value(restaurantId));
    }
}