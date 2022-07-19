package org.jakartaeerecipe.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="BOOK_STORE")
public class BookStore implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @Column(name="NAME")
    private String name;
    @Column(name="LOCATION_CITY")
    private String locationCity;
    @Column(name="LOCATION_STATE")
    private String locationState;
    @OneToMany(mappedBy="bookStore")
    private Set<BookCategory> categories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof BookStore)) {
            return false;
        }
        BookStore other = (BookStore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javaee7.entity.Factory[ id=" + id + " ]";
    }

    /**
     * @return the products
     */
    public Set<BookCategory> getCategories() {
        return categories;
    }

    /**
     * @param categories the categores to set
     */
    public void setCategories(Set<BookCategory> categories) {
        this.categories = categories;;
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
     * @return the locationCity
     */
    public String getLocationCity() {
        return locationCity;
    }

    /**
     * @param locationCity the locationCity to set
     */
    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    /**
     * @return the locationState
     */
    public String getLocationState() {
        return locationState;
    }

    /**
     * @param locationState the locationState to set
     */
    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }


}
