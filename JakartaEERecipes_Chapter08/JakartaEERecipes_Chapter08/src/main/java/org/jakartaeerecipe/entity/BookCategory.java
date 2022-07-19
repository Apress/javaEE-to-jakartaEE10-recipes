package org.jakartaeerecipe.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="BOOK_CATEGORY")
public abstract class BookCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @Column(name="NAME")
    private String name;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STORE_ID", referencedColumnName="ID")
    private BookStore bookStore;

    public BookCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookCategory)) {
            return false;
        }
        BookCategory other = (BookCategory) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javaee8recipes.entity.BookCateory[ id=" + getId() + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the bookStore
     */
    public BookStore getBookStore() {
        return bookStore;
    }

    /**
     * @param bookStore the bookStore to set
     */
    public void setBookStore(BookStore bookStore) {
        this.bookStore = bookStore;
    }

}
