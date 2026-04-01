package com.study.medicorehospitalmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.study.medicorehospitalmanagement.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("""
                SELECT MONTH(a.appointmenttime) as month, COUNT(a)
                FROM Appointment a
                GROUP BY MONTH(a.appointmenttime)
                ORDER BY MONTH(a.appointmenttime)
            """)
    List<Object[]> getMonthlyAppointments();
}