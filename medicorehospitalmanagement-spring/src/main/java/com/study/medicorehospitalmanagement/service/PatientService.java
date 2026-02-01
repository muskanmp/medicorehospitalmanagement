package com.study.medicorehospitalmanagement.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.study.medicorehospitalmanagement.dto.PatientResponseDto;
import com.study.medicorehospitalmanagement.entities.Patient;
import com.study.medicorehospitalmanagement.repositories.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Patient getPatientById(Integer id) {
        Patient p1= patientRepository.findById(id).orElse(null);

        // Patient p2= patientRepository.findById(id).orElse(null);
        
        // p1.setName("XYZ");
        System.out.println(p1);
        
        return p1;
    }

    public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize) {

        return patientRepository.findAllPatients(PageRequest.of(pageNumber, pageSize))
        .stream().map(patient -> modelMapper.map(patient, PatientResponseDto.class))
        .collect(Collectors.toList());
    }
}
