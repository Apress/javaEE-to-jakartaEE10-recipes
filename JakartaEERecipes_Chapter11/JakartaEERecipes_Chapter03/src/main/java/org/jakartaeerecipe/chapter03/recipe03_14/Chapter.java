package org.jakartaeerecipe.chapter03.recipe03_14;

import java.io.Serializable;

public record Chapter(int getChapterNumber, String getTitle, String getDescription) implements Serializable {

}
