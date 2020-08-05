package com.ssafy.travel.service;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.travel.controller.BookController;
import com.ssafy.travel.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtServiceImpl implements JwtService {
	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public String create(User user) {
		final JwtBuilder builder = Jwts.builder();
		builder.setHeaderParam("typ", "JWT");
		
		builder.setSubject("Login Token")
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
				.claim("User", user);
		
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		final String jwt = builder.compact();
		return jwt;
	}
	
	@Override
	public User getUser(String jwt) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException("decoding failed");
		}
		
		return objectMapper.convertValue(claims.getBody().get("User"), User.class);
	}
	
	@Override
	public Map<String, Object> get(String jwt) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		} catch (final Exception e) {
			throw new RuntimeException();
		}
		
		return claims.getBody();
	}
}
