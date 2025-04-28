package com.example.plazoleta.ms_plazoleta.integration;

import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DishIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private Long restaurantId;

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
    }

    @Test
    void shouldCreateDishSuccessfully() throws Exception {
        String dishJson = """
                {
                  "name": "Dish Uno",
                  "price": 15000,
                  "description": "Plato delicioso de prueba",
                  "imageUrl": "http://image.com/plato.png",
                  "category": "Entradas"
                }
                """;

        mockMvc.perform(post("/restaurants/" + restaurantId + "/dishes")
                        .header("ownerId", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dishJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dish Uno"))
                .andExpect(jsonPath("$.price").value(15000))
                .andExpect(jsonPath("$.description").value("Plato delicioso de prueba"))
                .andExpect(jsonPath("$.imageUrl").value("http://image.com/plato.png"))
                .andExpect(jsonPath("$.category").value("Entradas"))
                .andExpect(jsonPath("$.restaurantId").value(restaurantId));
    }
}