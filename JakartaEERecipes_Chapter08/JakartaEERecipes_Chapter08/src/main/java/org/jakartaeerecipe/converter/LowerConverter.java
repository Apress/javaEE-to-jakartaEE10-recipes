package org.jakartaeerecipe.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("org.jakartaeerecipe.converter.LowerConverter")
public class LowerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String value) {
        // Return String value in upper case
        return value.toString().toLowerCase();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object value) {

        // Return String value
        return value.toString().toLowerCase();

    }
}
