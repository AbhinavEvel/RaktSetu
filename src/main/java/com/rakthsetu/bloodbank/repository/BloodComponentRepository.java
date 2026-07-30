package com.rakthsetu.bloodbank.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rakthsetu.bloodbank.entity.BloodComponent;



@Repository   // Spring ko batata hai: yeh ek DAO (Data Access Object) bean hai
public interface BloodComponentRepository extends JpaRepository<BloodComponent, Integer> {
    // Abhi khaali — JpaRepository se hi save(), findAll(), findById(), delete() mil jaate hain
}
