package org.jakartaeerecipe.chapter07.entity.key;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

@Embeddable
public class AuthorWorkPKEmbedded implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigInteger bookId;
    private BigInteger authorId;

    public AuthorWorkPKEmbedded() {
    }

    public AuthorWorkPKEmbedded(BigInteger bookId, BigInteger authorId){
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

    public int hashCode() {
        return bookId.hashCode() + authorId.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthorWorkPKEmbedded)) {
            return false;
        }
        AuthorWorkPKEmbedded pk = (AuthorWorkPKEmbedded) obj;
        return (((Objects.equals(bookId, ((AuthorWorkPKEmbedded) obj).getBookId())))
                && ((Objects.equals(authorId, ((AuthorWorkPKEmbedded) obj).getAuthorId()))));
    }

}