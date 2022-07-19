package org.jakartaeerecipe.chapter07.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.jakartaeerecipe.chapter07.entity.key.AuthorWorkPKEmbedded;
import org.jakartaeerecipe.chapter07.entity.key.AuthorWorkPKNonEmbedded;

@IdClass(AuthorWorkPKNonEmbedded.class)
@Entity
@Table(name = "AUTHOR_WORK_LEGACY")
@NamedQuery(name = "AuthorWorkLegacy.findAll", query = "SELECT a FROM AuthorWorkLegacy a")
public class AuthorWorkLegacy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "BOOK_ID")
    private BigInteger bookId;
    @Id
    @Column(name= "AUTHOR_ID")
    private BigInteger authorId;
    public AuthorWorkLegacy() {
    }
    public AuthorWorkLegacy(BigInteger bookId, BigInteger authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public BigInteger getBookId() {
        return bookId;
    }

    public void setBookId(BigInteger bookId) {
        this.bookId = bookId;
    }

    public BigInteger getAuthorId() {
        return authorId;
    }

    public void setAuthorId(BigInteger authorId) {
        this.authorId = authorId;
    }
}

