package com.dging.dgingmarket.util.converter;

import com.dging.dgingmarket.enums.ImageType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Converter
public class ImageTypeAttributeConverter implements AttributeConverter<ImageType, String> {

    @Override
    public String convertToDatabaseColumn(ImageType attribute) {
        if(ObjectUtils.isEmpty(attribute))
            return null;
        return attribute.getValue();
    }

    @Override
    public ImageType convertToEntityAttribute(String dbData) {
        if(!StringUtils.hasText(dbData))
            return null;
        return ImageType.find(dbData);
    }
}
