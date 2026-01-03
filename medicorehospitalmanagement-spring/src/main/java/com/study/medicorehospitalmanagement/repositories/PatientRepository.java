package com.study.medicorehospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.medicorehospitalmanagement.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
