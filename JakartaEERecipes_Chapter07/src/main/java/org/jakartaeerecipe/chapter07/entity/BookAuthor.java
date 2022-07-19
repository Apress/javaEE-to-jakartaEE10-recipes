package org.jakartaeerecipe.chapter07.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "BOOK_AUTHOR")
// Named queries are covered in Recipe 7-9
@NamedQuery(name = "BookAuthor.findAll", query = "SELECT b FROM BookAuthor b")
@NamedQuery(name = "BookAuthor.findById", query = "SELECT b FROM BookAuthor b WHERE b.id = :id")
@NamedQuery(name = "BookAuthor.findByLast", query = "SELECT b FROM BookAuthor b WHERE b.lastname = :last")
@NamedQuery(name = "BookAuthor.findByFirst", query = "SELECT b FROM BookAuthor b WHERE b.firstname = :first")
public class BookAuthor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_author_s_generator")
    @SequenceGenerator(name = "book_author_s_generator", sequenceName = "book_author_s", allocationSize = 1)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    @Size(max = 30)
    @Column(name = "LASTNAME")
    private String lastname;

    @Size(max = 30)
    @Column(name = "FIRSTNAME")
    private String firstname;

    @Lob
    @Column(name = "BIO")
    private String bio;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(o instanceof BookAuthor)) {
            return false;
        }
        BookAuthor other = (BookAuthor) o;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);        return hash;
    }

    @Override
    public String toString() {
        return "BookAuthor[ id=" + id + " ]";
    }

}
