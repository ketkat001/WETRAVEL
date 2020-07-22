package com.ssafy.travel.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.travel.dto.Article;

@Repository
public class ArticleDaoImpl implements ArticleDao{
	@Autowired
	SqlSession sqlSession;

	@Override
	public int registArticle(Article article) {
		return sqlSession.insert("m_article.registArticle",article);
	}

	@Override
	public List<Article> getArticleList() {
		return sqlSession.selectList("m_article.getArticleList");
	}

	@Override
	public Article getArticle(String articleno) {
		return sqlSession.selectOne("m_article.getArticle", articleno);
	}

	@Override
	public int deleteArticle(String articleno) {
		return sqlSession.delete("m_article.deleteArticle",articleno);
	}

	@Override
	public int modifyArticle(Article article) {
		return sqlSession.update("m_article.modifyArticle",article);
	}
}
