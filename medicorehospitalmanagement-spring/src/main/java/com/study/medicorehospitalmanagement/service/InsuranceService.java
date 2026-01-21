package com.study.medicorehospitalmanagement.service;

import org.springframework.stereotype.Service;

import com.study.medicorehospitalmanagement.entities.Insurance;
import com.study.medicorehospitalmanagement.entities.Patient;
import com.study.medicorehospitalmanagement.repositories.InsuranceRepository;
import com.study.medicorehospitalmanagement.repositories.PatientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor

public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Integer patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        patient.setInsurance(insurance);
        // insurance.setPatient(patient); // bidirectional consistency maintainence

        System.out.println("ins: "+insurance);

        return patient;
    }

    @Transactional
    public Patient DissociateInsuranceFromPatient(Integer patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        patient.setInsurance(null);
        return patient;
    }
}
