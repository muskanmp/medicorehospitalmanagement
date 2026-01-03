package com.study.medicorehospitalmanagement;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.medicorehospitalmanagement.entities.Patient;
import com.study.medicorehospitalmanagement.repositories.PatientRepository;

@SpringBootTest
public class Patienttest {

    @Autowired
    PatientRepository patientRepository;

    @Test
    public void testPatientRepository() {

        List<Patient> patients = patientRepository.findAll();
        System.out.println(patients);
    }
}
