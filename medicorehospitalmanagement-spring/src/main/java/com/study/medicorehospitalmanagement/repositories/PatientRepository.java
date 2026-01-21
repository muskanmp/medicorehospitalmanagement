package com.study.medicorehospitalmanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.medicorehospitalmanagement.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findById(Integer patientId);

}
