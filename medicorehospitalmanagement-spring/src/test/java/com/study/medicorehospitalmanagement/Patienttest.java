package com.study.medicorehospitalmanagement;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.medicorehospitalmanagement.entities.Patient;
import com.study.medicorehospitalmanagement.repositories.PatientRepository;
import com.study.medicorehospitalmanagement.service.PatientService;

@SpringBootTest
public class Patienttest {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientService patientService;

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
}