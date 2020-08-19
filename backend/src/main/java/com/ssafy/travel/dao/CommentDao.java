package com.ssafy.travel.dao;

import java.util.List;

import com.ssafy.travel.dto.Comment;

public interface CommentDao {
	public int registComment(Comment comment);
	public List<Comment> getCommentList(int articleno);
	public int modifyComment(Comment comment);
	public int deleteComment(int commentno);
}
