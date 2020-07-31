package com.ssafy.travel.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.travel.dto.Article;
import com.ssafy.travel.dto.Score;

@Repository
public class ArticleDaoImpl implements ArticleDao{
	@Autowired
	SqlSession sqlSession;

	@Override
	public int registArticle(Article article) {
		return sqlSession.insert("m_article.registArticle",article);
	}

	@Override
	public List<Article> getArticleList(int bookno) {
		return sqlSession.selectList("m_article.getArticleList", bookno);
	}

	@Override
	public int deleteArticle(int articleno) {
		return sqlSession.delete("m_article.deleteArticle",articleno);
	}

	@Override
	public int modifyArticle(Article article) {
		return sqlSession.update("m_article.modifyArticle",article);
	}

	@Override
	public Article getArticleDetail(int articleno) {
		return sqlSession.selectOne("m_article.getArticleDetail", articleno);
	}

	@Override
	public int registerScore(Score score) {
		// score을 article_score 디비에 저장한다 
		// 저장된 score article에 반영하기 위해 article테이블에 score를 avg로 갱신한다
		// 저장된 score book에 반영하기 위해  book테이블에 score를 avg로 갱신한다
		try {
			int result = sqlSession.insert("m_article.registScore",score);
			sqlSession.update("m_article.modifyArticleScore",score.getArticleno());
			sqlSession.update("m_article.modifyBookScore",score.getArticleno());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
