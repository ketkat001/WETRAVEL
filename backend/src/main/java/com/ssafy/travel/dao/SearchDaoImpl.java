package com.ssafy.travel.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDaoImpl implements SearchDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<String> getCityList(String province) {
		return sqlSession.selectList("m_search.getCityList", province);
	}
}
