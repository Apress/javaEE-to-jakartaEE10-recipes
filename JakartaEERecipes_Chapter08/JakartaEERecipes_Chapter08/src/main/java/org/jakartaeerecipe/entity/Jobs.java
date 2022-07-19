package org.jakartaeerecipe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "JOBS")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Jobs.findAll", query = "SELECT j FROM Jobs j"),
        @NamedQuery(name = "Jobs.findByJobId", query = "SELECT j FROM Jobs j WHERE j.jobId = :jobId"),
        @NamedQuery(name = "Jobs.findByTitle", query = "SELECT j FROM Jobs j WHERE j.title = :title"),
        @NamedQuery(name = "Jobs.findByDivision", query = "SELECT j FROM Jobs j WHERE j.division = :division"),
        @NamedQuery(name = "Jobs.findBySalary", query = "SELECT j FROM Jobs j WHERE j.salary = :salary")})
public class Jobs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    // @NotNull
    @Column(name = "JOB_ID")
    private Integer jobId;

    @Size(max = 50)
    @Column(name = "TITLE")
    private String title;

    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DIVISION")
    private String division;

    @Basic(optional = false)
    //@NotNull
    @Column(name = "SALARY")
    private BigDecimal salary;

    @OneToMany(mappedBy = "job")
    private List<Employee> employees;

    public Jobs() {
    }

    public Jobs(Integer jobId) {
        this.jobId = jobId;
    }

    public Jobs(Integer jobId, String division, BigDecimal salary) {
        this.jobId = jobId;
        this.division = division;
        this.salary = salary;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobId != null ? jobId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobs)) {
            return false;
        }
        Jobs other = (Jobs) object;
        if ((this.jobId == null && other.jobId != null) || (this.jobId != null && !this.jobId.equals(other.jobId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javaee7.entity.Jobs[ jobId=" + jobId + " ]";
    }
}
