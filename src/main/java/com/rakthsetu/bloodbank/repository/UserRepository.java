package com.rakthsetu.bloodbank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rakthsetu.bloodbank.entity.Role;
import com.rakthsetu.bloodbank.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Login ke liye — email se user dhundna hoga
    Optional<User> findByEmail(String email);

    // Register ke waqt check karna hoga — yeh email pehle se exist toh nahi karta
    boolean existsByEmail(String email);
    
 // Saare Admins ki list nikalne ke liye
    List<User> findByRole(Role role);
}
