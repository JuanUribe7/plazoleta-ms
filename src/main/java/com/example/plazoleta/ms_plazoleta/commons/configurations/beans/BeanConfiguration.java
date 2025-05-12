package com.example.plazoleta.ms_plazoleta.commons.configurations.beans;

import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.CreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.DishValidationFields;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.ListDishesServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.UpdateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.order.*;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.*;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishQueryPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderQueryPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantQueryPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.OrderTraceabilityPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.services.DishValidationService;
import com.example.plazoleta.ms_plazoleta.domain.services.RestaurantValidationService;
import com.example.plazoleta.ms_plazoleta.domain.services.ValidationFieldsService;
import com.example.plazoleta.ms_plazoleta.domain.usecases.dish.CreateDishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.dish.ListDishesUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.dish.UpdateDishUseCase;
import com.example.plazoleta.ms_plazoleta.domain.usecases.order.*;
import com.example.plazoleta.ms_plazoleta.domain.usecases.restaurant.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public DishValidationFields dishValidationFields() {
        return new DishValidationService(new ValidationFieldsService());
    }

    @Bean
    public RestaurantValidationFields restaurantValidationFields() {
        return new RestaurantValidationService(new ValidationFieldsService());
    }

    @Bean
    public CreateDishServicePort createDishServicePort(DishPersistencePort dishPersistencePort,
                                                       RestaurantPersistencePort restaurantPersistencePort,
                                                       DishValidationFields validationFieldServices) {
        return new CreateDishUseCase(dishPersistencePort, restaurantPersistencePort, validationFieldServices);
    }

    @Bean
    public UpdateDishServicePort updateDishServicePort(DishPersistencePort dishPersistencePort,
                                                       RestaurantPersistencePort restaurantPersistencePort,
                                                       DishValidationFields validationFieldServices) {
        return new UpdateDishUseCase(dishPersistencePort, validationFieldServices, restaurantPersistencePort);
    }

    @Bean
    public ListDishesServicePort listDishesServicePort(DishQueryPort dishQueryPort) {
        return new ListDishesUseCase(dishQueryPort);
    }

    // 🍴 Restaurants
    @Bean
    public CreateRestaurantServicePort createRestaurantServicePort(RestaurantPersistencePort restaurantPersistencePort,
                                                                   UserValidationPort userValidationPort,
                                                                   RestaurantValidationFields restaurantValidationFields) {
        return new CreateRestaurantUseCase(restaurantPersistencePort, userValidationPort,restaurantValidationFields);
    }

    @Bean
    public ListRestaurantsServicePort listRestaurantsServicePort(RestaurantQueryPort restaurantQueryPort) {
        return new ListRestaurantsUseCase(restaurantQueryPort);
    }

    @Bean
    public AssignEmployeeServicePort assignEmployeeServicePort(RestaurantPersistencePort restaurantPersistencePort) {
        return new AssignEmployeeToRestaurantUseCase(restaurantPersistencePort);
    }

    @Bean
    public ValidateRestaurantExistsServicePort validateRestaurantExistsServicePort(RestaurantPersistencePort restaurantPersistencePort) {
        return new ValidateRestaurantExistsUseCase(restaurantPersistencePort);
    }

    // 📦 Orders
    @Bean
    public CreateOrderServicePort createOrderServicePort(OrderPersistencePort orderPersistencePort,
                                                         DishPersistencePort dishPersistencePort,
                                                         OrderTraceabilityPort orderTraceabilityPort) {
        return new CreateOrderUseCase(orderPersistencePort, dishPersistencePort, orderTraceabilityPort);
    }

    @Bean
    public AssignOrderServicePort assignOrderServicePort(OrderPersistencePort orderPersistencePort,
                                                         RestaurantPersistencePort restaurantPersistencePort,
                                                         OrderTraceabilityPort orderTraceabilityPort) {
        return new AssignOrderUseCase(orderPersistencePort, restaurantPersistencePort, orderTraceabilityPort);
    }

    @Bean
    public DeliverOrderServicePort deliverOrderUseCase(OrderPersistencePort orderPort,
                                                       RestaurantPersistencePort restaurantPort,
                                                       OrderTraceabilityPort traceabilityPort) {
        return new DeliverOrderUseCase(orderPort, restaurantPort, traceabilityPort);
    }

    @Bean
    public CancelOrderServicePort cancelOrderUseCase(OrderPersistencePort orderPort,
                                                     OrderTraceabilityPort traceabilityPort) {
        return new CancelOrderUseCase(orderPort, traceabilityPort);
    }

    @Bean
    public MarkOrderAsReadyServicePort markOrderAsReadyServicePort(OrderPersistencePort orderPersistencePort,
                                                                   RestaurantPersistencePort restaurantPersistencePort,
                                                                   UserValidationPort userValidationPort,
                                                                   OrderTraceabilityPort orderTraceabilityPort) {
        return new MarkOrderAsReadyUseCase(orderPersistencePort, restaurantPersistencePort, userValidationPort, orderTraceabilityPort);
    }
    @Bean
    public ListOrdersByStatusServicePort listOrdersByStatusServicePort(OrderQueryPort orderQueryPort,
                                                                       RestaurantPersistencePort restaurantPersistencePort) {

        return new ListOrdersByStatusUseCase(orderQueryPort, restaurantPersistencePort);

}
}
