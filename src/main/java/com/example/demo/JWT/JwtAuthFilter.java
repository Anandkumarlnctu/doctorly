package com.example.demo.JWT;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Models.User_model;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Security.UsernamePasswordAuthenticationToken;
import com.example.demo.*;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtAuthHelper help;
    
    @Autowired
    UserRepository userrepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestHeader = request.getHeader("Cookie");
        if(requestHeader==null) {
        	requestHeader=request.getHeader("Authorization");
        }
        String username = null;
        String token = null;
         
        // Check if the request header contains the Bearer token
        if (requestHeader != null ) {
        	if(requestHeader.startsWith("jwt=")){
        		 token = requestHeader.substring(4);
        	}
        	else if(requestHeader.startsWith("Bearer")) {
        		token=requestHeader.substring(7);
        	}
           
            System.out.println(token);
//            System.out.println(token+ " "+ "line46"); // Extract the token from the header
            username = help.getusername_from_token(token);  // Extract the registration number from the token
             
            // If the registration number is not null and there is no existing authentication
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                   System.out.println("filtering");
                // Check if the token is expired
                if (!help.istokenexpired(token)) {

                    // Validate the token and load the hospital details using registration number and hospital name
                    User_model user=userrepo.findByUsername(username);

                    if (user!=null) {
                       
                      System.out.println("in");
                        // Create the custom authentication token with hospital details
                        UsernamePasswordAuthenticationToken authToken = 
                                new UsernamePasswordAuthenticationToken (
                                        username, 
                                        user.getPassword()
                                       
                                );
                        authToken.setAuthenticated(true);
                        // Set the details in the authentication token
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        // Set the authentication in the SecurityContextHolder
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    } else {
                        // If the hospital does not exist, throw an exception or handle it accordingly
                        throw new BadCredentialsException("Invalid credentials");
                    }
                } 
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}

