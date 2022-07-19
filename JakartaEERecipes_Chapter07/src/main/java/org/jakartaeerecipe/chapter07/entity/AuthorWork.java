package org.jakartaeerecipe.chapter07.entity;

import jakarta.persistence.*;
import org.jakartaeerecipe.chapter07.entity.key.AuthorWorkPKEmbedded;

import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "AUTHOR_WORK")
@NamedQuery(name = "AuthorWork.findAll", query = "SELECT a FROM AuthorWork a")
public class AuthorWork implements Serializable {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private BookAuthor authorId;

    @Column(name = "BOOK_ID")
    private BigInteger bookId;

    // You can use an embedded ID in-place of a standard Id if a table
    // contains more than one column to compose a primary key.  Comment
    // out along with the getters and setters to use a non-embeddable primary key.

    @EmbeddedId
    private AuthorWorkPKEmbedded embeddedId;


    public AuthorWork() {
    }

    public AuthorWork(BigInteger bookId, BigInteger authorId) {
        this.embeddedId = new AuthorWorkPKEmbedded(bookId, authorId);
    }

    public AuthorWorkPKEmbedded getEmbeddedId() {
        return embeddedId;
    }

    public void setEmbeddedId(AuthorWorkPKEmbedded embeddedId) {
        this.embeddedId = embeddedId;
    }

    public BigInteger getBookId() {
        return bookId;
    }

    public void setBookId(BigInteger bookId) {
        this.bookId = bookId;
    }

    public BookAuthor getAuthorId() {
        return authorId;
    }

    public void setAuthorId(BookAuthor authorId) {
        this.authorId = authorId;
    }
}
