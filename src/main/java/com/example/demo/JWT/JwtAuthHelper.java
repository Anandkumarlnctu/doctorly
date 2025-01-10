package com.example.demo.JWT;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.example.demo.Models.User_model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthHelper {

	public String secret="thisisacourseofdiningandcodingcodewellandhotosleepeatsleepcoderepeatbye";
	private static final long JWT_TOKEN_VALIDITY=30*30;
	
	public String getusername_from_token(String token) {
		Claims claim=getClaimsFromToken(token);
		return claim.getSubject();
		
	}
	
	public Claims getClaimsFromToken(String token) {
		Claims claim=Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token)
				.getBody();
		return claim;
	}
	public boolean istokenexpired(String token) {
		Claims claim=getClaimsFromToken(token);
		Date expdate=claim.getExpiration();
		return expdate.before(new Date()); 
		
	}

	

	public String generateToken(User_model user) {
		LocalDateTime expirationTime = LocalDateTime.now().plusSeconds(JWT_TOKEN_VALIDITY);
		Date expirationDate = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());
		Map<String,Object>claims=new HashMap<>();
		return Jwts.builder().setClaims(claims)
		.setSubject(user.getUsername())
		.setExpiration(expirationDate)
		.signWith(new SecretKeySpec(secret.getBytes(),SignatureAlgorithm.HS512.getJcaName()),SignatureAlgorithm.HS512)
		.compact();
		
	}
}

