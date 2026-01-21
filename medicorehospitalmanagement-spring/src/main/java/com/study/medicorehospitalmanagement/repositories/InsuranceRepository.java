package com.study.medicorehospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.medicorehospitalmanagement.entities.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

}