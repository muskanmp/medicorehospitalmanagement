/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.study.medicorehospitalmanagement.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Muskaan
 */
@Entity
@Table(name = "department")
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")})
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    // @NotNull
    // @Size(min = 1, max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @JoinTable(name = "doctordeprtment", joinColumns = {
        @JoinColumn(name = "department", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "doctor", referencedColumnName = "id")})
    @ManyToMany
    private List<Doctor> doctorList;
    @JoinColumn(name = "headdoctor", referencedColumnName = "id")
    @OneToOne
    private Doctor headdoctor;

    public Department() {
    }

    public Department(Integer id) {
        this.id = id;
    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public Doctor getHeaddoctor() {
        return headdoctor;
    }

    public void setHeaddoctor(Doctor headdoctor) {
        this.headdoctor = headdoctor;
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
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.study.medicorehospitalmanagement.entities.Department[ id=" + id + " ]";
    }
    
}
