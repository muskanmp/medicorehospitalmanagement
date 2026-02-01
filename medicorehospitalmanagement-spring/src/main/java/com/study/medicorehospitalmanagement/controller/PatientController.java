package com.study.medicorehospitalmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.medicorehospitalmanagement.dto.PatientResponseDto;
import com.study.medicorehospitalmanagement.entities.Patient;
import com.study.medicorehospitalmanagement.service.PatientService;

    import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
// @RequestMapping("/patients")
public class PatientController {
    
    
    private final PatientService patientService;

    @GetMapping("/admin/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(patientService.getAllPatients(pageNumber, pageSize));
    }

    @GetMapping("/public/patients")
    public ResponseEntity<Patient> getAllPatientsPublic(
    ) {
        return ResponseEntity.ok(patientService.getPatientById(1));
    }
}
