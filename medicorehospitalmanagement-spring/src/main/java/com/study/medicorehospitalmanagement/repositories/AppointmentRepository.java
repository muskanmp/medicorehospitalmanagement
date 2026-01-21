package com.study.medicorehospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.medicorehospitalmanagement.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}