package com.study.medicorehospitalmanagement.service;

import org.springframework.stereotype.Service;

import com.study.medicorehospitalmanagement.entities.Patient;
import com.study.medicorehospitalmanagement.repositories.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public Patient getPatientById(Integer id) {
        Patient p1= patientRepository.findById(id).orElse(null);

        Patient p2= patientRepository.findById(id).orElse(null);
        
        p1.setName("XYZ");
        System.out.println(p1);
        
        return p1;
    }
}
