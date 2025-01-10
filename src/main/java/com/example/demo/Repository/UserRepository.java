package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.User_model;

public interface UserRepository extends JpaRepository<User_model,Integer> {

	User_model findByUsernameAndPassword(String username,String password);
	User_model findByUsername(String username);
}
