package org.jakartaeerecipe.chapter03.recipe03_03;

import java.io.Serializable;

public record Author(String getFirst, String getLast, String getBio) implements Serializable {
}
