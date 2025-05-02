package com.example.plazoleta.ms_plazoleta.domain.utils.converters;

import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
@Component
public class CategoryTypeConverter implements Converter<String, CategoryType> {

    @Override
    public CategoryType convert(String source) {
        for (CategoryType type : CategoryType.values()) {
            if (type.name().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid category. Valid options: STARTER, DRINK, MAIN_COURSE, DESSERT");
    }
}