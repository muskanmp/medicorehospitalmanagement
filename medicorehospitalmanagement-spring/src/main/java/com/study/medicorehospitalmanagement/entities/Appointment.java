/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.study.medicorehospitalmanagement.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.time.LocalDateTime;

// import org.antlr.v4.runtime.misc.NotNull;

/**
 *
 * @author Muskaan
 */
@Entity
@Builder
@AllArgsConstructor
@Table(name = "appointment")
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    // @NotNull
    @Column(name = "appointmenttime",nullable = false)
    // @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime appointmenttime;
    // @Size(max = 500)
    @Column(name = "reason", length = 500)
    private String reason;
    @JoinColumn(name = "doctor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Doctor doctor;
    @JoinColumn(name = "patient", referencedColumnName = "patientid")
    @ManyToOne(optional = false)
    private Patient patient;

    public Appointment() {
    }

    public Appointment(Integer id) {
        this.id = id;
    }

    public Appointment(Integer id, LocalDateTime appointmenttime) {
        this.id = id;
        this.appointmenttime = appointmenttime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime  getAppointmenttime() {
        return appointmenttime;
    }

    public void setAppointmenttime(LocalDateTime  appointmenttime) {
        this.appointmenttime = appointmenttime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.study.medicorehospitalmanagement.entities.Appointment[ id=" + id + " ]";
    }
    
}
