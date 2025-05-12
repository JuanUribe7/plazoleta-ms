package com.example.plazoleta.ms_plazoleta;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.commons.constants.ResponseMessages;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.DishRepository;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Prueba de integración que verifica todo el flujo de creación de un plato
 * desde la autenticación con el microservicio de usuarios hasta su persistencia en base de datos.
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DishFlowIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DishRepository dishRepository;

    // Datos del restaurante y usuario owner para usar en la prueba
    private static final Long RESTAURANT_ID = 1L;
    private static final String OWNER_EMAIL = "propietario1@gmail.com";
    private static final String OWNER_PASSWORD = "1042241877Ju@n";

    /**
     * Antes de cada prueba, asegura que el restaurante exista en base de datos.
     * Si no existe, lo crea.
     */
    @BeforeEach
    void setup() {
        if (!restaurantRepository.existsById(RESTAURANT_ID)) {
            RestaurantEntity restaurant = new RestaurantEntity();
            restaurant.setId(RESTAURANT_ID);
            restaurant.setName("Demo Restaurant");
            restaurantRepository.save(restaurant);
        }

        // Eliminar plato si existe
        dishRepository.findByNameAndRestaurantId("Arroz con pollo", RESTAURANT_ID)
                .ifPresent(dishRepository::delete);
    }


    /**
     * Prueba principal: verifica que se pueda crear un plato exitosamente
     * haciendo login real contra ms-usuarios, generando el token JWT
     * y enviando la petición autenticada al endpoint de creación de plato.
     */
    @Test
    void shouldCreateDishSuccessfully() throws Exception {
        // Paso 1: hacer login contra el microservicio usuarios usando RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String loginJson = """
            {
                "email": "%s",
                "password": "%s"
            }
            """.formatted(OWNER_EMAIL, OWNER_PASSWORD);

        HttpEntity<String> loginRequest = new HttpEntity<>(loginJson, headers);
        ResponseEntity<String> loginResponse = restTemplate.postForEntity("http://localhost:8081/auth/login", loginRequest, String.class);

        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Extraer el token del JSON de respuesta
        JsonNode loginBody = objectMapper.readTree(loginResponse.getBody());
        String token = loginBody.get("token").asText();
        assertThat(token).isNotBlank();

        // Paso 2: crear el DTO del plato
        DishRequestDto dishDto = new DishRequestDto(
                "Arroz con pollo",            // Nombre
                25000,                        // Precio
                "Arroz con pollo tradicional",// Descripción
                "https://example.com/img.jpg",// Imagen
                "MAIN",                       // Categoría (enum en backend)
                RESTAURANT_ID                 // Restaurante al que pertenece
        );

        // Paso 3: realizar el POST con MockMvc al endpoint /restaurants/{id}/dishes
        mockMvc.perform(post("/restaurants/{restaurantId}/dishes", RESTAURANT_ID)
                        .header("Authorization", "Bearer " + token) // enviar JWT
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dishDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(ResponseMessages.DISH_CREATED));

        // Paso 4: validar que el plato se guardó correctamente en la base de datos
        DishEntity saved = dishRepository.findByNameAndRestaurantId("Arroz con pollo", RESTAURANT_ID).orElse(null);
        assertThat(saved).isNotNull();
        assertThat(saved.getPrice()).isEqualTo(25000);
        assertThat(saved.getDescription()).contains("pollo");
    }
}
