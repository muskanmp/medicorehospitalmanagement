package com.study.medicorehospitalmanagement.entities;

import java.io.Serializable;
import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.*;

@Entity

@Table(name = "patient")
@NamedQueries({
@NamedQuery(name = "Patient.findAll", query = "SELECT e FROM Patient e")})


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Patient implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientid;

    private String name;

    private LocalDate birthdate;

    private String email;

    private String gender;
}
