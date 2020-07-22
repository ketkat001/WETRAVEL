package com.ssafy.travel.service;

import java.util.List;

import com.ssafy.travel.dto.Article;

public interface ArticleService {
	public int registArticle(Article article); 
	public List<Article> getArticleList();
	public Article getArticle(String articleno);
	public int deleteArticle(String articleno);
	public int modifyArticle(Article article);
}
