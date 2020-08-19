package com.ssafy.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.travel.dao.CommentDao;
import com.ssafy.travel.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao commentDao;
	
	@Override
	public boolean registComment(Comment comment) {
		return commentDao.registComment(comment) == 1;
	}

	@Override
	public List<Comment> getCommentList(int articleno) {
		return commentDao.getCommentList(articleno);
	}

	@Override
	public boolean modifyComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.modifyComment(comment) == 1;
	}

	@Override
	public boolean deleteComment(int commentno) {
		return commentDao.deleteComment(commentno) == 1;
	}

}
