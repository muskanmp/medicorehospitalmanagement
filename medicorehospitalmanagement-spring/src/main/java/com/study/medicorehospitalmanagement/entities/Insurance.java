/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.study.medicorehospitalmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Muskaan
 */
@Entity
@Builder
@AllArgsConstructor
@Table(name = "insurance")
@NamedQueries({
    @NamedQuery(name = "Insurance.findAll", query = "SELECT i FROM Insurance i")})
public class Insurance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "policynumber", length = 50)
    private String policynumber;
    @Basic(optional = false)
    @Column(name = "provider", length = 100)
    private String provider;
    @Basic(optional = false)
    @Column(name = "validuntil")
    // @Temporal(TemporalType.DATE)
    private LocalDate validuntil;
    @Column(name = "createdat")
    private LocalDateTime createdat;
    @OneToOne(mappedBy = "insurance")
    private Patient patient;

    public Insurance() {
    }

    public Insurance(Integer id) {
        this.id = id;
    }

    // public Insurance(Integer id, String policynumber, String provider, LocalDate validuntil) {
    //     this.id = id;
    //     this.policynumber = policynumber;
    //     this.provider = provider;
    //     this.validuntil = validuntil;
    // }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolicynumber() {
        return policynumber;
    }

    public void setPolicynumber(String policynumber) {
        this.policynumber = policynumber;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public LocalDate getValiduntil() {
        return validuntil;
    }

    public void setValiduntil(LocalDate validuntil) {
        this.validuntil = validuntil;
    }

    public LocalDateTime getCreatedat() {
        return createdat;
    }

    public void setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
        if (!(object instanceof Insurance)) {
            return false;
        }
        Insurance other = (Insurance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.study.medicorehospitalmanagement.entities.Insurance[ id=" + id + " ]";
    }
    
}
