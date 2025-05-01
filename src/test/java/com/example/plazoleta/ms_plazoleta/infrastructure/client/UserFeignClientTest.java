package com.example.plazoleta.ms_plazoleta.infrastructure.client;

import com.example.plazoleta.ms_plazoleta.domain.ports.in.CreateRestaurantServicePort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserFeignClientMockTest {

    @MockBean
    private UserFeignClient userFeignClient;

    @Autowired
    private CreateRestaurantServicePort createRestaurantServicePort;

    @Test
    void testFeignClientMock() {
        // Arrange: simular respuesta del FeignClient
        Mockito.when(userFeignClient.getRoleByUser(1L)).thenReturn("ADMIN");

        // Act: llamar el método que usa el feign client
        String role = userFeignClient.getRoleByUser(1L);

        // Assert
        assertEquals("ADMIN", role);
    }
}
