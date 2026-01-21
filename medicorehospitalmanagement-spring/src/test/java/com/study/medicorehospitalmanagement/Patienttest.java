package com.study.medicorehospitalmanagement;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.medicorehospitalmanagement.entities.Appointment;
import com.study.medicorehospitalmanagement.entities.Patient;
import com.study.medicorehospitalmanagement.repositories.PatientRepository;
import com.study.medicorehospitalmanagement.service.AppointmentService;
import com.study.medicorehospitalmanagement.service.PatientService;

@SpringBootTest
public class Patienttest {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;

    @Test
    public void testPatientRepository() {

        List<Patient> patients = patientRepository.findAll();
        System.out.println(patients);

    }

    @Test
    public void testPatientTransactionService() {
        Patient p1 = patientService.getPatientById(1);
        System.out.println(p1);
    }

    @Test
    public void testcreateAppointment(){

        Appointment appointment = Appointment.builder()
        .appointmenttime(LocalDateTime.of(2026,01,22,14,0))
        .reason("Regular Checkup 2").build();

        var newappointment = appointmentService.createnewAppointment(appointment, 1, 1);
        System.out.println(newappointment);
    }
}