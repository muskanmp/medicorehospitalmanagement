package com.study.medicorehospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.medicorehospitalmanagement.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
