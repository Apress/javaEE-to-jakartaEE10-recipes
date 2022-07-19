package org.jakartaee10recipe.chapter04.recipe04_06;

public class Chapter implements java.io.Serializable {
    private int chapterNumber = 0;
    private String title      = null;
    private String description = null;

    public Chapter(int chapter, String title, String desc){
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
}
