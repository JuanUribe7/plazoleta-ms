package com.example.plazoleta.ms_plazoleta.domain.usecases.order;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.order.ListOrdersByStatusServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderQueryPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.RelationValidator;


public class ListOrdersByStatusUseCase implements ListOrdersByStatusServicePort {

    private final OrderQueryPort queryPort;
    private final RestaurantPersistencePort restaurantPort;

    public ListOrdersByStatusUseCase(OrderQueryPort queryPort, RestaurantPersistencePort restaurantPort) {
        this.queryPort = queryPort;
        this.restaurantPort = restaurantPort;
    }

    @Override
    public PaginatedResult<Order> findByStatusAndRestaurant(String status, Long restaurantId, Long employeeId, int page, int size) {
        Restaurant restaurant = ExistenceValidator.getIfPresent(
                restaurantPort.findById(restaurantId),
                ExceptionMessages.RESTAURANT_NOT_FOUND
        );

        RelationValidator.validateCondition(
                restaurant.getEmployeeIds().contains(employeeId),
                ExceptionMessages.EMPLOYEE_NOT_ASSIGNED_TO_RESTAURANT
        );
        return queryPort.findAllByStatusAndRestaurant(status, restaurantId, page, size);
    }
}
