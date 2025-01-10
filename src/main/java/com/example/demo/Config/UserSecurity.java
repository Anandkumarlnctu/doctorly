package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.example.demo.JWT.JwtAuthFilter;



@Configuration
@EnableWebSecurity

public class UserSecurity {

	@Autowired JwtAuthFilter filter;
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable()) // Disable CSRF protection for simplicity (not recommended for production)
	            .authorizeHttpRequests(auth -> auth
	            		.requestMatchers("/signin","/signup","/home","/auth/login","/register","/CSS/**","/JS/**","/images/**").permitAll()
	            		.anyRequest().authenticated() // Require authentication for all requests
	            )
	            .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Enable HTTP Basic authentication
       
	        http.addFilterBefore(filter, AbstractPreAuthenticatedProcessingFilter.class);
	        return http.build(); // Return the SecurityFilterChain instance
	
}
	 @Bean
	    public AuthenticationManager authman(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
}
