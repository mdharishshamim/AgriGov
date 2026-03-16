package com.agrigov.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrigov.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
