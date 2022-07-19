package org.jakartaeerecipe.entity;

import org.jakartaeerecipe.annotation.JavaBookTitle;
import org.jakartaeerecipe.annotation.ValidNumChapters;
import org.jakartaeerecipe.annotation.group.BookGroup;
import org.jakartaeerecipe.entity.BookAuthor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;


/**
 *
 * @author juneau
 */
@Entity
@Table(name = "BOOK")
@NamedNativeQuery(
        name="allBooks",
        query = "select id, title, description " +
                "FROM BOOK " +
                "ORDER BY id",
        resultClass=Book.class)
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")})

@XmlRootElement
@ValidNumChapters
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    @JavaBookTitle(message = "Book Title Should Contain The Word Java")
    @Size(max = 150, message="The title cannot exceed {max} characters, current title is $'{validatedValue}'",
            groups={BookGroup.class})
    @Column(name = "TITLE")
    protected String title;

    @Size(max = 500)
    @Column(name = "IMAGE")
    private String image;

    @NotNull(groups={BookGroup.class})
    @Column(name = "NUM_CHAPTERS")
    private int numChapters;

    @Column(name = "NUM_PAGES")
    private int numPages;

    @Lob
    @NotNull(groups={BookGroup.class})
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PUBLISH_DATE")
    private LocalDate publishDate;

    @ManyToMany(mappedBy="books")
    private Set<BookAuthor> authors;

    @OneToMany(mappedBy="book", cascade=CascadeType.ALL)
    private List<Chapter> chapters = null;

    public Book() {
    }

    public Book(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javaee8recipes.entity.Book[ id=" + id + " ]";
    }

    /**
     * @return the authors
     */
    @XmlTransient
    public Set<BookAuthor> getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(Set<BookAuthor> authors) {
        this.authors = authors;
    }

    /**
     * @return the chapters
     */
    @XmlTransient
    public List<Chapter> getChapters() {
        return chapters;
    }

    /**
     * @param chapters the chapters to set
     */
    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public int getNumPages(){
        return this.numPages;
    }

    public void setNumPages(int numPages){
        this.numPages = numPages;
    }

    public int getNumChapters(){
        return this.numChapters;
    }

    public void setNumChapters(int numChapters){
        this.numChapters = numChapters;
    }

}
