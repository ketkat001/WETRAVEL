package com.ssafy.travel.service;

import java.util.List;

import com.ssafy.travel.dto.Comment;

public interface CommentService {
	public boolean registComment(Comment comment);
	public List<Comment> getCommentList(int articleno);
	public boolean modifyComment(Comment comment);
	public boolean deleteComment(int commentno);
}
