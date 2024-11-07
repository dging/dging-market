package com.dging.dgingmarket.util.converter;

import com.dging.dgingmarket.enums.MainCategory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Converter
public class MainCategoryAttributeConverter implements AttributeConverter<MainCategory, String> {

    @Override
    public String convertToDatabaseColumn(MainCategory attribute) {
        if(ObjectUtils.isEmpty(attribute))
            return null;
        return attribute.getValue();
    }

    @Override
    public MainCategory convertToEntityAttribute(String dbData) {
        if(!StringUtils.hasText(dbData))
            return null;
        return MainCategory.find(dbData);
    }
}
