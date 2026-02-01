package com.study.medicorehospitalmanagement.dto;

import java.time.LocalDate;

import com.study.medicorehospitalmanagement.entities.type.BloodGroupType;

import lombok.Data;

@Data
public class PatientResponseDto {
    private Long patientid;
    private String name;
    private String gender;
    private LocalDate birthdate;
    private BloodGroupType bloodgroup;
}
