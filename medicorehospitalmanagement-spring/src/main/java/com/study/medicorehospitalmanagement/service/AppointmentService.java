package com.study.medicorehospitalmanagement.service;

import org.springframework.stereotype.Service;

import com.study.medicorehospitalmanagement.entities.Appointment;
import com.study.medicorehospitalmanagement.entities.Doctor;
import com.study.medicorehospitalmanagement.entities.Patient;
import com.study.medicorehospitalmanagement.repositories.AppointmentRepository;
import com.study.medicorehospitalmanagement.repositories.DoctorRepository;
import com.study.medicorehospitalmanagement.repositories.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createnewAppointment(Appointment appointment, Integer patientId, Integer doctorId) {

        // Implementation for creating a new appointment
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID"));
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        return appointmentRepository.save(appointment);

    }

    @Transactional
    public Appointment UpdateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
