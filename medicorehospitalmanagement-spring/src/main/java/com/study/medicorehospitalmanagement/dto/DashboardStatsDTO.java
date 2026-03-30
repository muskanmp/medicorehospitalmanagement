package com.study.medicorehospitalmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsDTO {

    private long totalPatients;
    private long totalDoctors;
    private long totalAppointments;
    private long totalDepartments;

}
