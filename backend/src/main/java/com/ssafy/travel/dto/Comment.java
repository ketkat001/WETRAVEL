package com.ssafy.travel.dto;

import java.sql.Date;

public class Comment {
	private int commentno;
	private int articleno;
	private String writer;
	private Date writedate;
	private String text;
	
	public Comment(int commentno, int articleno, String writer, Date writedate, String text) {
		super();
		this.commentno = commentno;
		this.articleno = articleno;
		this.writer = writer;
		this.writedate = writedate;
		this.text = text;
	}
	public int getCommentno() {
		return commentno;
	}
	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Comment [commentno=" + commentno + ", articleno=" + articleno + ", writer=" + writer + ", writedate="
				+ writedate + ", text=" + text + "]";
	}
}
