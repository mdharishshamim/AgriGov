package com.agrigov.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrigov.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long>{

}
