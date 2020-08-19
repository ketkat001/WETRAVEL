package com.ssafy.travel.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.travel.dto.Comment;

@Repository
public class CommentDaoImpl implements CommentDao {
	@Autowired
	SqlSession sqlSession;

	@Override
	public int registComment(Comment comment) {
		return sqlSession.insert("m_comment.registComment", comment);
	}

	@Override
	public List<Comment> getCommentList(int articleno) {
		System.out.println("DAO");
		return sqlSession.selectList("m_comment.getCommentList", articleno);
	}

	@Override
	public int modifyComment(Comment comment) {
		return sqlSession.update("m_comment.modifyComment", comment);
	}

	@Override
	public int deleteComment(int commentno) {
		return sqlSession.delete("m_comment.deleteComment", commentno);
	}
}
