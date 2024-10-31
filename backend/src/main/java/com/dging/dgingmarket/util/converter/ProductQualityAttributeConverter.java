package com.dging.dgingmarket.util.converter;

import com.dging.dgingmarket.util.enums.ProductQuality;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ProductQualityAttributeConverter implements AttributeConverter<ProductQuality, String> {

    @Override
    public String convertToDatabaseColumn(ProductQuality attribute) {
        if(ObjectUtils.isEmpty(attribute))
            return null;
        return attribute.getValue();
    }

    @Override
    public ProductQuality convertToEntityAttribute(String dbData) {
        if(!StringUtils.hasText(dbData))
            return null;
        return ProductQuality.find(dbData);
    }
}
