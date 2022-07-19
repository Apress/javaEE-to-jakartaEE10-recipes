package org.jakartaeerecipe.chapter14.recipe14_08;

import jakarta.json.bind.annotation.JsonbTransient;

public class Dog {

    private String name;

    private int age;

    private String gender;

    @JsonbTransient
    private String color;
}
