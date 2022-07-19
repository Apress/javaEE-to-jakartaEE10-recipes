package org.jakartaeerecipe.chapter06.entity;

import java.util.List;

public class Book implements java.io.Serializable{
    private int id;
    private String title;
    private String description;
    private String image;
    private List<Chapter> chapters;


    public Book(){
        this.title = null;
        this.image = null;
        this.description = null;
    }

    public Book(int id, String title, String image, String description){
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public Book(String title, String image){
        this.title = title;
        this.image = image;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the chapters
     */
    public List<Chapter> getChapters() {
        return chapters;
    }

    /**
     * @param chapters the chapters to set
     */
    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}