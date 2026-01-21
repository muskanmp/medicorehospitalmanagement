package com.study.medicorehospitalmanagement.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import com.study.medicorehospitalmanagement.entities.type.BloodGroupType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Table(
        name = "patient"
        ,uniqueConstraints = {
//                @UniqueConstraint(name = "unique_patient_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = {"name", "birthdate"})
        },
        indexes = {
                @Index(name = "idx_patient_birth_date", columnList = "birthdate")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "patientid")
    private Integer patientid;
    @Basic(optional = false)
    // @NotNull
    // @Size(min = 1, max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "birthdate")
    // @Temporal(TemporalType.DATE)
    private Date birthdate;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")
    // //if the field contains email address consider using this annotation to enforce field validation
    
    @Column(name = "bloodgroup")
    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodgroup;
    // @Basic(optional = false)
    @JoinColumn(name = "insurance", referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Insurance insurance;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    private List<Appointment> appointmentList;

    @Column(unique = true)
    private String email;

    private String gender;

    // @CreationTimestamp
    @Column(name = "createdat", updatable = false)
    private LocalDateTime createdat;

    // public Patient(Integer patientid) {
    //     this.patientid = patientid;
    // }

    // public Patient(Integer patientid, String name, LocalDateTime createdat) {
    //     this.patientid = patientid;
    //     this.name = name;
    //     this.createdat = createdat;
    // }

    // public Integer getPatientid() {
    //     return patientid;
    // }

    // public void setPatientid(Integer patientid) {
    //     this.patientid = patientid;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public Date getBirthdate() {
    //     return birthdate;
    // }

    // public void setBirthdate(Date birthdate) {
    //     this.birthdate = birthdate;
    // }

    // public String getEmail() {
    //     return email;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }

    // public String getGender() {
    //     return gender;
    // }

    // public void setGender(String gender) {
    //     this.gender = gender;
    // }

    // public Insurance getInsurance() {
    //     return insurance;
    // }

    // public void setInsurance(Insurance insurance) {
    //     this.insurance = insurance;
    // }

    // public List<Appointment> getAppointmentList() {
    //     return appointmentList;
    // }

    // public void setAppointmentList(List<Appointment> appointmentList) {
    //     this.appointmentList = appointmentList;
    // }

    // @Override
    // public int hashCode() {
    //     int hash = 0;
    //     hash += (patientid != null ? patientid.hashCode() : 0);
    //     return hash;
    // }

    // @Override
    // public boolean equals(Object object) {
    //     // TODO: Warning - this method won't work in the case the id fields are not set
    //     if (!(object instanceof Patient)) {
    //         return false;
    //     }
    //     Patient other = (Patient) object;
    //     if ((this.patientid == null && other.patientid != null) || (this.patientid != null && !this.patientid.equals(other.patientid))) {
    //         return false;
    //     }
    //     return true;
    // }

    // @Override
    // public String toString() {
    //     return "com.study.medicorehospitalmanagement.entities.Patient[ patientid=" + patientid + " ]";
    // }

    // public static long getSerialversionuid() {
    //     return serialVersionUID;
    // }

    // public BloodGroupType getBloodgroup() {
    //     return bloodgroup;
    // }

    // public void setBloodgroup(BloodGroupType bloodgroup) {
    //     this.bloodgroup = bloodgroup;
    // }

    // public LocalDateTime getCreatedat() {
    //     return createdat;
    // }

    // public void setCreatedat(LocalDateTime createdat) {
    //     this.createdat = createdat;
    // }

}