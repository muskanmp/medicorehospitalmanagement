package com.study.medicorehospitalmanagement.service;

import java.time.Month;
import java.util.List;

import org.springframework.stereotype.Service;

import com.study.medicorehospitalmanagement.dto.DashboardStatsDTO;
import com.study.medicorehospitalmanagement.dto.MonthlyAppointmentDTO;
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
                departments);
    }

    public List<MonthlyAppointmentDTO> getMonthlyAppointments() {

        List<Object[]> result = appointmentRepository.getMonthlyAppointments();

        return result.stream().map(obj -> new MonthlyAppointmentDTO(
                getMonthName((Integer) obj[0]),
                (Long) obj[1])).toList();
    }

    private String getMonthName(int month) {
        return Month.of(month).name();
    }
}
