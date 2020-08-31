package com.ssafy.travel.dao;

import com.ssafy.travel.dto.User;

public interface UserDao {
	User getUserOne(String common, String col);
	User getLogin(String email, String password);
	int userJoin(User user);
	public int deleteUser(String email);
	public User getUserDetail(String email);
	public int modifyUser(User user);
}
