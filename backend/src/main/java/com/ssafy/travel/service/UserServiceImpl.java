package com.ssafy.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.travel.dao.UserDaoImpl;
import com.ssafy.travel.dto.Book;
import com.ssafy.travel.dto.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDaoImpl userDAO;
	
	@Override
	public User getUserOne(String common, String col) {
		return userDAO.getUserOne(common, col);
	}
	
	@Override
	public int userJoin(User user) {
		return userDAO.userJoin(user);
	}

	@Override
	public boolean deleteUser(String email) {
		return userDAO.deleteUser(email) == 1;
	}

	@Override
	public User getUserDetail(String email) {
		return userDAO.getUserDetail(email);
	}

	@Override
	public boolean modifyUser(User user) {
		return userDAO.modifyUser(user) == 1;
	}
}
