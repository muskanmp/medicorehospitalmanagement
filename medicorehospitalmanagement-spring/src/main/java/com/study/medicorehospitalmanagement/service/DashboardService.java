package com.study.medicorehospitalmanagement.service;

import org.springframework.stereotype.Service;

import com.study.medicorehospitalmanagement.dto.DashboardStatsDTO;
import com.study.medicorehospitalmanagement.repositories.AppointmentRepository;
import com.study.medicorehospitalmanagement.repositories.DepartmentRepository;
import com.study.medicorehospitalmanagement.repositories.DoctorRepository;
import com.study.medicorehospitalmanagement.repositories.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {
    
    
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final DepartmentRepository departmentRepository;

    public DashboardStatsDTO getDashboardStats() {

        long patients = patientRepository.count();
        long doctors = doctorRepository.count();
        long appointments = appointmentRepository.count();
        long departments = departmentRepository.count();

        return new DashboardStatsDTO(
                patients,
                doctors,
                appointments,
                departments
        );
    }
}
