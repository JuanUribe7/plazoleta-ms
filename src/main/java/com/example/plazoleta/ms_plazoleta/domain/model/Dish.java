package com.example.plazoleta.ms_plazoleta.domain.model;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish.DescriptionValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish.DishFieldValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish.*;


public class Dish {
    private final Long id;
    private final String name;
    private  Integer price;
    private  String description;
    private final String imageUrl;
    private final boolean active;
    private final Long restaurantId;
    private final CategoryType category;



    public Dish(Dish existingDish, Integer price, String description) {
        this.id = existingDish.getId();
        this.name = existingDish.getName();
        this.price = price != null ? price : existingDish.getPrice();
        this.description = description != null ? description : existingDish.getDescription();
        this.imageUrl = existingDish.getImageUrl();
        this.active = existingDish.isAvailable();
        this.restaurantId = existingDish.getRestaurantId();
        this.category = existingDish.getCategory();
    }


    public Dish(Long id, String name, Integer price, String description,
                String imageUrl, boolean active, Long restaurantId, CategoryType category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.active = active;
        this.restaurantId = restaurantId;
        this.category = category;
    }



    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public boolean isAvailable() { return active; }
    public Long getRestaurantId() { return restaurantId; }
    public CategoryType getCategory() { return category; }

    public Dish create(RestaurantPersistencePort restaurantPort,
                       DishPersistencePort dishPort,
                       Long ownerId) {
        DishFieldValidator.validate(this);
        DishCreationValidator.validate(this, ownerId, restaurantPort, dishPort);
        return dishPort.saveDish(this);
    }

    public Dish update(DishPersistencePort dishPort, RestaurantPersistencePort restaurantPort,
                       Long dishId, Long ownerId) {
        DishUpdateValidator.validate(this, dishId, ownerId, dishPort, restaurantPort);
        return dishPort.updateDish(this);
    }

    public Dish changeStatus(boolean active, Long restaurantId, Long ownerId,
                             RestaurantPersistencePort restaurantPort) {
        DishStatusValidator.validate(this, restaurantId, ownerId, restaurantPort);
        return new Dish(
                this.id, this.name, this.price, this.description, this.imageUrl,
                active, this.restaurantId, this.category
        );
    }
    public void changePrice(Integer newPrice) {
        if (newPrice == null || newPrice <= 0) {
            throw new IllegalArgumentException(ErrorFieldsMessages.DISH_PRICE_INVALID);
        }
        this.price = newPrice;
    }

    public void changeDescription(String newDesc) {
        DescriptionValidator.validate(newDesc);
        this.description = newDesc;
    }
}
