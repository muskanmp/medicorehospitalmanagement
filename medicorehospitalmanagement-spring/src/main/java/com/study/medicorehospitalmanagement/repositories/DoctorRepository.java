package com.study.medicorehospitalmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.study.medicorehospitalmanagement.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query("""
                SELECT d.name, COUNT(a)
                FROM Appointment a
                JOIN a.doctor d
                GROUP BY d.name
                ORDER BY COUNT(a) DESC
            """)
    List<Object[]> getDoctorWorkload();
}
