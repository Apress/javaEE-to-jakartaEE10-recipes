package org.jakartaeerecipe.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author juneau
 */
@Entity
@Table(name = "BOOK_AUTHOR")
@NamedQueries({
        @NamedQuery(name = "BookAuthor.findAll", query = "SELECT b FROM BookAuthor b")})
@SqlResultSetMapping(name="authorBooks",
        entities= {
                @EntityResult(entityClass=org.jakartaeerecipe.entity.Book.class, fields={
                        @FieldResult(name="id", column="BOOK_ID"),
                        @FieldResult(name="title", column="TITLE")
                }),
                @EntityResult(entityClass=org.jakartaeerecipe.entity.BookAuthor.class, fields={
                        @FieldResult(name="id", column="AUTHOR_ID"),
                        @FieldResult(name="first", column="FIRSTNAME"),
                        @FieldResult(name="last", column="LASTNAME")
                })
        })
// Not yet supported as of 04-20-12
//@NamedStoredProcedure(name=”createUser”, procedureName=”CREATE_USER”,
//resultClass=void.class, resultSetMapping=” “, returnsResultSet=false, parameters = {
//    @StoredProcedureParameter(name=”username”, queryParameter=”USER”),
//    @StoredProcedureParameter(name=”password”, queryParameter=”PASS”)
//}

public class BookAuthor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    @Size(max = 30)
    @Column(name = "LASTNAME")
    private String last;

    @Size(max = 30)
    @Column(name = "FIRSTNAME")
    private String first;

    @Lob
    @Column(name = "BIO")
    private String bio;

    @ManyToMany
    @JoinTable(name="AUTHOR_WORK",
            joinColumns=
            @JoinColumn(name="AUTHOR_ID", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="BOOK_ID", referencedColumnName="ID"))
    private Set<Book> books;

    public BookAuthor() {
    }

    public BookAuthor(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the bookAuthorId fields are not set
        if (!(object instanceof BookAuthor)) {
            return false;
        }
        BookAuthor other = (BookAuthor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javaee8recipes.entity.BookAuthor[ id=" + id + " ]";
    }

    /**
     * @return the books
     */
    public Set<Book> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(Set<Book> books) {
        this.books = books;
    }



}
