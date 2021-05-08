package com.cts.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.training.bean.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	
}

