package com.ssafy.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.travel.dao.SearchDao;

@Service
public class SearchServiceImpl implements SearchService{
	@Autowired
	private SearchDao searchDao;
	
	@Override
	public List<String> getCityList(String province) {
		return searchDao.getCityList(province);
	}
}
