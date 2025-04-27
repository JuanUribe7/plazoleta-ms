package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.IllegalLogoException;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort,
                       IRestaurantPersistencePort restaurantPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        ;
    }

    @Override
    public Dish createDish(Dish dish){
        validate(dish);
        dish.setActive(true);

        return  dishPersistencePort.saveDish(dish);
    }

    public void validate(Dish dish) {
        Restaurant restaurante = restaurantPersistencePort.findById(dish.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("El restaurante no existe"));

        if (!restaurante.getOwnerId().equals(dish.getOwnerId())) {
            throw new IllegalArgumentException("No es propietario del restaurante.");
        }
        if (dishPersistencePort.findByName(dish.getName()).isPresent()) {
            throw new IllegalArgumentException("El nombre del plato ya está registrado.");
        }

        if (dish.getName() == null || dish.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        // Validación de URL de imagen
        System.out.println("Validando URL imagen: " + dish.getImageUrl());
        if (dish.getImageUrl() == null || dish.getImageUrl().trim().isEmpty()) {
            throw new IllegalLogoException("La URL del logo no puede estar vacía");
        }
        if (dish.getImageUrl().contains("localhost") || dish.getImageUrl().contains("127.0.0.1")) {
            throw new IllegalArgumentException("La URL del logo no puede ser local");
        }
        if (!dish.getImageUrl().matches("^(http|https)://.*$")) {
            throw new IllegalArgumentException("La URL del logo debe ser válida y comenzar con http o https");
        }
        if (!dish.getImageUrl().matches(".*\\.(png|jpg|jpeg|svg)$")) {
            throw new IllegalArgumentException("La URL del logo debe terminar en .png, .jpg, .jpeg o .svg");
        }

        // Validación de categoría
        System.out.println("Validando categoría: " + dish.getCategory());
        if (dish.getCategory() == null || dish.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía");
        }
        if (!dish.getCategory().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            throw new IllegalArgumentException("La categoría solo puede contener letras y espacios");
        }
        if (dish.getCategory().length() < 3 || dish.getCategory().length() > 30) {
            throw new IllegalArgumentException("La categoría debe tener entre 3 y 30 caracteres");
        }

        // Validación de descripción
        System.out.println("Validando descripción: " + dish.getDescription());
        if (dish.getDescription() == null || dish.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (!dish.getDescription().matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ.,\\s]+$")) {
            throw new IllegalArgumentException("La descripción solo puede contener letras, números, espacios, puntos y comas");
        }
        if (dish.getDescription().length() < 10 || dish.getDescription().length() > 200) {
            throw new IllegalArgumentException("La descripción debe tener entre 10 y 200 caracteres");
        }

        // Validación de precio
        System.out.println("Validando precio: " + dish.getPrice());
        if (dish.getPrice() <= 0) {
            throw new IllegalArgumentException("Precio debe ser mayor a 0");
        }


    }


    }







