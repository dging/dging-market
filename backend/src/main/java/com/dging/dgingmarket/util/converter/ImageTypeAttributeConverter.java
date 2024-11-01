package com.dging.dgingmarket.util.converter;

import com.dging.dgingmarket.util.enums.ImageType;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
