package com.study.medicorehospitalmanagement.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Appointment> appointmentList;

    @Column(unique = true)
    private String email;

    private String gender;

    // @CreationTimestamp
    @Column(name = "createdat", updatable = false)
    private LocalDateTime createdat;

    @OneToOne
    private User user; 

}