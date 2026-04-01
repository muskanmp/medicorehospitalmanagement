package com.study.medicorehospitalmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.medicorehospitalmanagement.dto.DashboardStatsDTO;
import com.study.medicorehospitalmanagement.dto.DoctorWorkloadDTO;
import com.study.medicorehospitalmanagement.dto.MonthlyAppointmentDTO;
import com.study.medicorehospitalmanagement.service.DashboardService;
import com.study.medicorehospitalmanagement.service.DoctorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;
    private final DoctorService doctorService;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDTO> getStats() {
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }

    @GetMapping("/doctor-stats")
    public ResponseEntity<List<DoctorWorkloadDTO>> getDoctorWorkload() {
        return ResponseEntity.ok(doctorService.getDoctorWorkload());
    }

    @GetMapping("/monthly-appointments")
    public ResponseEntity<List<MonthlyAppointmentDTO>> getMonthlyAppointments() {
        return ResponseEntity.ok(dashboardService.getMonthlyAppointments());
    }
}
