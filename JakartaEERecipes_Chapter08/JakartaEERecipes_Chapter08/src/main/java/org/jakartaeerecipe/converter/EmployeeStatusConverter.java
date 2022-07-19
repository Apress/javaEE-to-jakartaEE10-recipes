package org.jakartaeerecipe.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EmployeeStatusConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean value) {
        if(value){
            return "ACTIVE";
        } else {
            return "INACTIVE";
        }
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        return s.equals("ACTIVE");
    }
}
