package com.agrigov.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrigov.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

}
