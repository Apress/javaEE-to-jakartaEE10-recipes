package org.jakartaeerecipe.chapter07.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "AUTHOR")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="author_s_generator",
            sequenceName="author_s", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="author_s_generator")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    @Size(max = 30)
    @Column(name = "LAST")
    private String last;

    @Size(max = 30)
    @Column(name = "FIRST")
    private String first;

    @Lob
    @Column(name = "BIO")
    private String bio;
    @OneToOne
    private AuthorDetail authorId;

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "AUTHOR_authorDetail",
            joinColumns = @JoinColumn(name = "author_ID"),
            inverseJoinColumns = @JoinColumn(name = "authorDetail_ID"))
    private AuthorDetail authorDetail;

    public AuthorDetail getAuthorDetail() {
        return authorDetail;
    }

    public void setAuthorDetail(AuthorDetail authorDetail) {
        this.authorDetail = authorDetail;
    }

    public Author() {
    }


}
