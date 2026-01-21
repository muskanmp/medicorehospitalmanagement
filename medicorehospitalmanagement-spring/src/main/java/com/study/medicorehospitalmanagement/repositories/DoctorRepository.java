package com.study.medicorehospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.medicorehospitalmanagement.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
