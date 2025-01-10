package com.example.demo.CONTROLLERS;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/home")
	public String gethome() {
		return "home";
	}
	
	@GetMapping("/signup")
	public String getsignup()
	{
		return "signup";
	}
	@GetMapping("/signin")
	public String getsignin()
	{
		return "signin";
	}
	@GetMapping("/authhome")
	public String get_authenticatedhome()
	{
		return "authenticatedhome";
	}
	@GetMapping("/hospital_list")
	public String get_hospital()
	{
		return "hospital_list";
	}
	@GetMapping("/hello")
	public String get_hello()
	{
		return "hello";
	}
	
}
