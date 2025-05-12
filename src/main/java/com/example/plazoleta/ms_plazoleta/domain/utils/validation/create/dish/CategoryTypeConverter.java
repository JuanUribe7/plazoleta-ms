package com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.InvalidFieldException;
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
        throw new InvalidFieldException(ErrorFieldsMessages.DISH_CATEGORY_INVALID);
    }
}