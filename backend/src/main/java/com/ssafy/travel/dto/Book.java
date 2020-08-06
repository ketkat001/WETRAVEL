package com.ssafy.travel.dto;

public class Book {
	private int bookno;
	private String title;
	private String writer;
	private String writedate;
	private String province;
	private String city;
	private String startdate;
	private String description;

	private double score;
	
	public Book() {
		super();
	}

	public Book(int bookno, String title, String writer, String writedate, String province, String city,
			String startdate, String description, double score) {
		super();
		this.bookno = bookno;
		this.title = title;
		this.writer = writer;
		this.writedate = writedate;
		this.province = province;
		this.city = city;
		this.startdate = startdate;
		this.description = description;
		this.score = score;
	}
	
	public int getBookno() {
		return bookno;
	}
	public void setBookno(int bookno) {
		this.bookno = bookno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Book [bookno=" + bookno + ", title=" + title + ", writer=" + writer + ", writedate=" + writedate
				+ ", province=" + province + ", city=" + city + ", startdate=" + startdate + ", description="
				+ description + ", score=" + score + "]";
	}
	
}
