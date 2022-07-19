package org.jakartaeerecipe.chapter07.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "AUTHOR_DETAIL")
public class AuthorDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "author_detail_s_generator", sequenceName = "author__detail_s", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "author_detail_s_generator")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 200)
    @Column(name = "ADDRESS1")
    private String address1;
    @Size(max = 200)
    @Column(name = "ADDRESS2")
    private String address2;
    @Size(max = 250)
    @Column(name = "CITY")
    private String city;
    @Size(max = 2)
    @Column(name = "STATE")
    private String state;
    @Size(max = 10)
    @Column(name = "ZIP")
    private String zip;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Lob
    @Column(name = "NOTES")
    private String notes;

    @OneToOne(optional = false, mappedBy = "authorDetail")
    private Author authorId;

    public AuthorDetail() {
    }
}

