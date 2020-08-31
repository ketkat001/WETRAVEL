package com.ssafy.travel.service;

import java.util.List;

import com.ssafy.travel.dto.Book;
import com.ssafy.travel.dto.User;

public interface UserService {
	User getUserOne(String common, String col);
	User getLogin(String email, String password);
	int userJoin(User user);
	public boolean deleteUser(String email);
	public User getUserDetail(String email);
	public boolean modifyUser(User user);
}
