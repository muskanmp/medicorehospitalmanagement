package com.study.medicorehospitalmanagement.dto;

import java.util.Date;

import com.study.medicorehospitalmanagement.entities.type.BloodGroupType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {
    
    private String username;
    private String password;
    private String name;
    private String email;
    private Date birthdate;
    private String gender;
    BloodGroupType bloodgroup;

}
