package org.jakartaeerecipe.chapter03.recipe03_14;

import java.io.Serializable;
import java.util.List;

public record Book(String getTitle, String getImage, List<Chapter> getChapters) implements Serializable {
    public Book(String getTitle, String getImage) {
        this(getTitle, getImage, null);
    }
}
