/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.study.medicorehospitalmanagement.repositories;

import com.study.medicorehospitalmanagement.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Muskaan
 */
public interface UserRepository extends JpaRepository<User, Long>{

    public Optional<User> findByUsername(String username);
    
}
