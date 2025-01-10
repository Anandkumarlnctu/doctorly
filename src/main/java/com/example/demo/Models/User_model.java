package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User_model {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int user_id;
	private String username;
	private String password;
	private String email;
	
}
