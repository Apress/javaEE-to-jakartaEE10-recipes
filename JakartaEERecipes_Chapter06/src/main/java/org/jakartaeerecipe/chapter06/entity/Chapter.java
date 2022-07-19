package org.jakartaeerecipe.chapter06.entity;

/**
 * Chapter 5
 * @author juneau
 */
public class Chapter implements java.io.Serializable {
    private int id = -1;
    private int bookId = -1;
    private int chapterNumber = 0;
    private String title      = null;
    private String description = null;

    public Chapter(int chapter, String title, String desc){
        this.chapterNumber = chapter;
        this.title = title;
        this.description = desc;
    }

    public Chapter(int id, int bookId, int chapter, String title, String desc){
        this.id = id;
        this.bookId = bookId;
        this.chapterNumber = chapter;
        this.title = title;
        this.description = desc;
    }

    /**
     * @return the chapterNumber
     */
    public int getChapterNumber() {
        return chapterNumber;
    }

    /**
     * @param chapterNumber the chapterNumber to set
     */
    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
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

    /**
     * @return the bookId
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
