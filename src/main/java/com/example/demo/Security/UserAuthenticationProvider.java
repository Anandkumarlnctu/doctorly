package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.demo.DTO.JwtRequest;
import com.example.demo.Models.User_model;
import com.example.demo.Repository.UserRepository;

@Component
public class UserAuthenticationProvider {

	@Autowired UserRepository userrepo;
public boolean authenticate(JwtRequest jwtreq) throws AuthenticationException {
        
    	String username=jwtreq.getUsername();
    	String password=jwtreq.getPassword();
    	
        User_model user = userrepo.findByUsernameAndPassword(username, password);
        if (user!=null) {
        	          
        	return true;            
        } else {
        	System.out.println("not found");
            return false;
        }
    }
}
