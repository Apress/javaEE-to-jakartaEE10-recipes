package org.jakartaeerecipe.chapter07.entity.key;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/**
 * Non-Embeddable Primary Key class for AuthorWork
 *
 * @author juneau
 */
public class AuthorWorkPKNonEmbedded implements Serializable {
    private BigInteger bookId;
    private BigInteger authorId;
    public AuthorWorkPKNonEmbedded() {
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

