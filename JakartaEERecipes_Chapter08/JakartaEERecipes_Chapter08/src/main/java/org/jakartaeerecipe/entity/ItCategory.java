package org.jakartaeerecipe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@DiscriminatorValue("IT")
public class ItCategory extends BookCategory implements Serializable {

    @Column(name="GENRE")
    private String genre;

    @Column(name="DESCRIPTION")
    private String description;

    public ItCategory(){

    }

    /**
     * @return the color
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param color the color to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


}
