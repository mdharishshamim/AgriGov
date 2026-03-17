package com.agrigov.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrigov.model.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

}
