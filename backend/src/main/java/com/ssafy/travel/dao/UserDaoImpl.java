package com.ssafy.travel.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.travel.dto.Book;
import com.ssafy.travel.dto.User;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	public SqlSession sqlSession;
	
	public User getUserOne(String common, String col) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (col.equals("email")) {
			map.put("email", common);
		}
		else if (col.equals("nickname")) {
			map.put("nickname", common);
		}
		return sqlSession.selectOne("m_user.getUserOne", map);
	}
	
	public int userJoin(User user) {
		return sqlSession.insert("m_user.userJoin", user);
	}

	@Override
	public int deleteUser(String email) {
		return sqlSession.delete("m_user.deleteUser",email);
	}

	@Override
	public User getUserDetail(String email) {
		return sqlSession.selectOne("m_user.getUserDetail", email);
	}

	@Override
	public int modifyUser(User user) {
		return sqlSession.update("m_user.modifyUser",user);
	}
}
