package com.raktsetu.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raktsetu.backend.entity.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long>{
	Optional<Donor> findByUser_UserId(Long userId);
}
