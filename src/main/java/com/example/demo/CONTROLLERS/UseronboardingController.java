package com.example.demo.CONTROLLERS;
import com.example.demo.DTO.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.DTO.User_model_request;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/auth")
public class UseronboardingController {

    @Autowired 
    UserService userserv;

    @PostMapping(value="/register",consumes = "application/json")
    public ResponseEntity<?> register_user(@RequestBody User_model_request user) {
        try {
            userserv.register_user(user); // Register the user
            return ResponseEntity.ok("User registration successful"); // Success response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error registering user: " + e.getMessage()); // Error response
        }
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponse>login(@RequestBody JwtRequest jwtreq)
	{
		  
		return new ResponseEntity<>(userserv.login(jwtreq),HttpStatus.OK);
	}
}

