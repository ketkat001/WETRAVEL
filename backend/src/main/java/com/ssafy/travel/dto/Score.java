package com.ssafy.travel.dto;

public class Score {
	private int scoreno;
	private int score;
	private String author;
	private int articleno;
	
	public Score() {
		super();
	}
	
	public Score(int scoreno, int score, String author, int articleno) {
		super();
		this.scoreno = scoreno;
		this.score = score;
		this.author = author;
		this.articleno = articleno;
	}

	public int getScoreno() {
		return scoreno;
	}
	public void setScoreno(int scoreno) {
		this.scoreno = scoreno;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	@Override
	public String toString() {
		return "Score [scoreno=" + scoreno + ", score=" + score + ", author=" + author + ", articleno=" + articleno
				+ "]";
	}
}
