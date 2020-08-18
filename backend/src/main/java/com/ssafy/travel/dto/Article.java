package com.ssafy.travel.dto;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Article implements Serializable {
	private int articleno;
	private int bookno;
	private String title;
	private String writedate;
	private double score;
	private int score_count;
	private String text;
	private String traveldate;
	private int day;
	private byte[] img;
	
	@JsonIgnore
	private MultipartFile thumbnail;
	
	@JsonProperty("thumbnail")
	private String imgName;
	
	public Article() {
		super();
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg() {
		try {
			this.img = this.thumbnail.getBytes();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public MultipartFile getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName() {
		this.imgName = this.thumbnail.getOriginalFilename();
	}

	public Article(int articleno, int bookno, String title, String writedate, double score, int score_count, String text,
			String traveldate, int day, MultipartFile thumbnail) {
		super();
		this.articleno = articleno;
		this.bookno = bookno;
		this.title = title;
		this.writedate = writedate;
		this.score = score;
		this.score_count = score_count;
		this.text = text;
		this.traveldate = traveldate;
		this.day = day;
		this.thumbnail = thumbnail;
	}
	
	public int getScore_count() {
		return score_count;
	}

	public void setScore_count(int score_count) {
		this.score_count = score_count;
	}

	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
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
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getScoreCount() {
		return score_count;
	}
	public void setWeather(int score_count) {
		this.score_count = score_count;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTraveldate() {
		return traveldate;
	}
	public void setTraveldate(String traveldate) {
		this.traveldate = traveldate;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	@Override
	public String toString() {
		return "Article [articleno=" + articleno + ", bookno=" + bookno + ", title=" + title + ", writedate="
				+ writedate + ", score=" + score + ", score_count=" + score_count + ", text=" + text + ", traveldate="
				+ traveldate + ", day=" + day + "]";
	}
	
}
