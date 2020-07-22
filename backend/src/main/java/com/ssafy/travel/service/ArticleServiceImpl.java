package com.ssafy.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.travel.dao.ArticleDao;
import com.ssafy.travel.dto.Article;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	ArticleDao dao;

	@Override
	public int registArticle(Article article) {
		return dao.registArticle(article);
	}

	@Override
	public List<Article> getArticleList() {
		return dao.getArticleList();
	}

	@Override
	public Article getArticle(String articleno) {
		return dao.getArticle(articleno);
	}

	@Override
	public int deleteArticle(String articleno) {
		return dao.deleteArticle(articleno);
	}

	@Override
	public int modifyArticle(Article article) {
		return dao.modifyArticle(article);
	}
	
}
