package com.study.medicorehospitalmanagement;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.medicorehospitalmanagement.entities.Insurance;
import com.study.medicorehospitalmanagement.entities.Patient;
import com.study.medicorehospitalmanagement.repositories.InsuranceRepository;
import com.study.medicorehospitalmanagement.service.InsuranceService;

@SpringBootTest
public class Insurancetest {

    @Autowired
    InsuranceService insuranceService;
    
    @Autowired
    InsuranceRepository insuranceRepository;
    
    @Test
    public void testInsurance() {
        Insurance insurance = Insurance.builder()
                .policynumber("HDFC_1111")
                .provider("HDFC")
                .validuntil(LocalDate.of(2030, 12, 12))
                .createdat(LocalDateTime.now()) 
                .build();

                // System.out.println("Abc: "+insurance);
        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1);

        System.out.println(patient);

        // var newPatient = insuranceService.DissociateInsuranceFromPatient(patient.getPatientid());

        // System.out.println(newPatient);
    }
}
