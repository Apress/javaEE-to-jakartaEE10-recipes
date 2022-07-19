package org.jakartaeerecipe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "EMPLOYEE")
@XmlRootElement

@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
@NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id")
@NamedQuery(name = "Employee.findByFirst", query = "SELECT e FROM Employee e WHERE e.first = :first")
@NamedQuery(name = "Employee.findByLast", query = "SELECT e FROM Employee e WHERE e.last = :last")
@NamedQuery(name = "Employee.findByAge", query = "SELECT e FROM Employee e WHERE e.age = :age")
@NamedStoredProcedureQuery(name="createEmp", procedureName="CREATE_EMP")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    @Size(max = 30)
    @Column(name = "FIRSTNAME")
    private String first;

    @Size(max = 30)
    @Column(name = "LASTNAME")
    private String last;

    @Column(name = "AGE")
    private int age;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Jobs job;

    @Column(name = "STATUS")
    @Convert(converter = org.jakartaeerecipe.converter.EmployeeStatusConverter.class)
    private boolean status;


    public Employee() {
    }

    public Employee(BigDecimal id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javaee7.entity.Employee[ id=" + id + " ]";
    }

    /**
     * @return the status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the job
     */
    public Jobs getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Jobs job) {
        this.job = job;
    }
}
