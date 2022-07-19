package org.jakartaeerecipe.chapter02.recipe02_05;

import java.io.Serializable;
import java.util.*;

public class ConditionalClass implements Serializable {
    private String typename = null;
    public static Set<String> javaTypes = new HashSet<>();

    public ConditionalClass() {
        javaTypes.add("byte");
        javaTypes.add("short");
        javaTypes.add("int");
        javaTypes.add("long");
        javaTypes.add("float");
        javaTypes.add("double");
        javaTypes.add("boolean");
        javaTypes.add("char");
    }

    public static boolean isPrimitive(String value){
        return javaTypes.contains(value.toLowerCase());
    }
    /**
     * @return the typename
     */
    public String getTypename() {
        return typename;
    }

    /**
     * @param typename the typename to set
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }
}
