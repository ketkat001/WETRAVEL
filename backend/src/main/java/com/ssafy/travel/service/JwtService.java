package com.ssafy.travel.service;

import java.util.Map;

import com.ssafy.travel.dto.User;

public interface JwtService {
	String create(User user);
	void checkValid(String jwt);
	Map<String, Object> get(String jwt);
}
