package org.jakartaeerecipe.chapter06.recipe06_07;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "AUTHOR_WORK")

public class Author implements Serializable {
    //@OneToMany(mappedBy = "id")
    //private List<org.jakartaeerecipes.chapter06.recipe06_07.AuthorDetail> authorDetailList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="author_s_generator",sequenceName="author_s", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="author_s_generator")
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
    @OneToMany(mappedBy="author")
    private Set<AuthorDetail> authorDetail;

    public Author() {
    }

    public Author(BigDecimal id) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookAuthor06)) {
            return false;
        }
        Author other = (Author) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jakartaeeerecipes.chapter06.entity.recipe06_07.Author[ id=" + id + " ]";
    }


    /**
     * @return the authorId
     */
    public Set<AuthorDetail> getAuthorDetail() {
        return authorDetail;
    }

    /**
     * @param authorId the authorId to set
     */
    public void setAuthorDetail(Set<AuthorDetail> authorDetail) {
        this.authorDetail = authorDetail;
    }

//    @XmlTransient
//    public List<org.jakartaeerecipes.chapter06.recipe06_07.AuthorDetail> getAuthorDetailList() {
//        return authorDetailList;
//    }

//    public void setAuthorDetailList(List<org.jakartaeerecipes.chapter06.recipe06_07.AuthorDetail> authorDetailList) {
//        this.authorDetailList = authorDetailList;
//    }

}
