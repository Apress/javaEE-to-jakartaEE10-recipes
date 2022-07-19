package org.jakartaeerecipe.chapter07.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "CONTACT")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    @Size(max = 50)
    @Column(name = "FIRSTNAME")
    private String first;

    @Size(max = 50)
    @Column(name = "LASTNAME")
    private String last;

    @Size(max = 150)
    @Column(name = "EMAIL")
    private String email;

    @Size(max = 30)
    @Column(name = "PASSWORD")
    private String password;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Size(max = 150)
    @Column(name = "OCCUPATION")
    private String occupation;

    @Size(max = 1)
    @Column(name = "RECEIVE_NOTIFICATIONS")
    private String receiveNotifications;

    @Size(max = 1)
    @Column(name = "GENDER")
    private String gender;

    public Contact(BigDecimal id) {
        this.id = id;
    }

    public Contact() {

    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getReceiveNotifications() {
        return receiveNotifications;
    }

    public void setReceiveNotifications(String receiveNotifications) {
        this.receiveNotifications = receiveNotifications;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contact[ id=" + id + " ]";
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JakartaEERecipes_LOCAL");
        EntityManager em = emf.createEntityManager();
        queryAllContacts(em);
    }

    private static void queryAllContacts(EntityManager em) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("SELECT c FROM Contact c");
            List<Contact> contactList = (List<Contact>) query.getResultList();
            for (Contact contact : contactList) {
                System.out.print("Name:" + contact.getFirst() + " " + contact.getLast());
                System.out.println();
            }
            transaction.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
