package com.example.plazoleta.ms_plazoleta.domain.model;

import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant.RestaurantValidator;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private final Long id;
    private final String name;
    private final String nit;
    private final String address;
    private final String phone;
    private final String urlLogo;
    private final Long ownerId;
    private final List<Long> employeeIds;

    public Restaurant(Long id, String name, String nit, String address, String phone,
                      String urlLogo, Long ownerId, List<Long> employeeIds) {
        this.id = id;
        this.name = name;
        this.nit = nit;
        this.address = address;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.ownerId = ownerId;
        this.employeeIds = employeeIds != null ? employeeIds : new ArrayList<>();
    }

    public Restaurant assignEmployee(Long employeeId, Long ownerId, RestaurantPersistencePort restaurantPort) {
        RestaurantValidator.validateAssignment(this, employeeId, ownerId);
        addEmployee(employeeId);
        restaurantPort.saveRestaurant(this);
        return this;
    }

    public Restaurant create(RestaurantPersistencePort restaurantPort, UserValidationPort userPort) {
        RestaurantValidator.validateFields(this);
        RestaurantValidator.validateUniqueness(this, restaurantPort);
        RestaurantValidator.validateOwnerExists(this.ownerId, userPort);
        restaurantPort.saveRestaurant(this);
        return this;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getNit() { return nit; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getUrlLogo() { return urlLogo; }
    public Long getOwnerId() { return ownerId; }
    public List<Long> getEmployeeIds() { return employeeIds; }



    public Restaurant addEmployee(Long employeeId) {
        if (employeeIds.contains(employeeId)) {
            throw new IllegalArgumentException("Empleado ya asignado");
        }
        employeeIds.add(employeeId);
        return this;
    }
}

