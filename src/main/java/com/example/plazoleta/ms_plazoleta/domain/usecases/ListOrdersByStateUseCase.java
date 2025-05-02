package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.ListOrdersByStateServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderQueryPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.EmployeeAuthorizationValidator;

import java.util.Optional;

public class ListOrdersByStateUseCase implements ListOrdersByStateServicePort {

    private final OrderQueryPort orderQueryPort;
    private final RestaurantPersistencePort restaurantPort;


    public ListOrdersByStateUseCase(OrderQueryPort orderQueryPort, RestaurantPersistencePort restaurantPort) {
        this.orderQueryPort = orderQueryPort;
        this.restaurantPort = restaurantPort; // Initialize this with the appropriate implementation
    }

    @Override
    public PagedResult<Order> listOrdersByState(Long restaurantId, Long employeeId, Optional<OrderStatus> status, Pagination pagination) {
        EmployeeAuthorizationValidator.validateEmployeeBelongsToRestaurant(restaurantId, employeeId, restaurantPort);
        return orderQueryPort.findByRestaurantAndStatus(restaurantId, status, pagination);
    }
}