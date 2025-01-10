package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.JwtRequest;
import com.example.demo.DTO.JwtResponse;
import com.example.demo.DTO.User_model_request;
import com.example.demo.JWT.JwtAuthHelper;
import com.example.demo.Models.User_model;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Security.UserAuthenticationProvider;

@Service
public class UserService {

	@Autowired UserRepository userrepo;
	 @Autowired UserAuthenticationProvider manager;
	 @Autowired JwtAuthHelper helper;
	public void register_user(User_model_request user)
	{
		User_model usmod=new User_model();
		usmod.setEmail(user.getEmail());
		usmod.setPassword(user.getPassword());
		usmod.setUsername(user.getUsername());
		
		userrepo.save(usmod);
	}
	
		
		
		public JwtResponse login(JwtRequest jwtreq) throws BadCredentialsException {
			
			if(manager.authenticate(jwtreq)) {
				User_model user=userrepo.findByUsernameAndPassword(jwtreq.getUsername(), jwtreq.getPassword());
			 System.out.println("Login request received");
			    String token=helper.generateToken(user);
			    JwtResponse res=JwtResponse.builder().token(token).build();
			   
			    return res;
			    
				
			}
			else {
				System.out.println("Login request not received");
				throw new BadCredentialsException("Invalid credentials");
			}
			
			
		}
		
}
