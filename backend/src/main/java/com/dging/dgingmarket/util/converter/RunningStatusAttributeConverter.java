package com.dging.dgingmarket.util.converter;

import com.dging.dgingmarket.enums.RunningStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Converter
public class RunningStatusAttributeConverter implements AttributeConverter<RunningStatus, String> {

    @Override
    public String convertToDatabaseColumn(RunningStatus attribute) {
        if(ObjectUtils.isEmpty(attribute))
            return null;
        return attribute.getValue();
    }

    @Override
    public RunningStatus convertToEntityAttribute(String dbData) {
        if(!StringUtils.hasText(dbData))
            return null;
        return RunningStatus.find(dbData);
    }
}
