package com.ssafy.travel.dto;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.coobird.thumbnailator.Thumbnails;

public class Article implements Serializable {
	private int articleno;
	private int bookno;
	private String title;
	private Date writedate;
	private double score;
	private int score_count;
	private String text;
	private Date traveldate;
	private int day;
	private byte[] img;
	private String exiflat;
	private String exiflong;
	
	
	
	public String getExiflat() {
		return exiflat;
	}

	public void setExiflat(String exiflat) {
		this.exiflat = exiflat;
	}

	public String getExiflong() {
		return exiflong;
	}

	public void setExiflong(String exiflong) {
		this.exiflong = exiflong;
	}

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
			if (!this.thumbnail.isEmpty())
				this.img = this.thumbnail.getBytes();
			else
				this.img = null;
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
		if (this.thumbnail != null)
			this.imgName = this.thumbnail.getOriginalFilename();
	}

	public Article(int articleno, int bookno, String title, Date writedate, double score, int score_count, String text,
			Date traveldate, int day, MultipartFile thumbnail, String exiflong, String exiflat) {
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
		this.exiflat = exiflat;
		this.exiflong = exiflong;
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
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
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
	public Date getTraveldate() {
		return traveldate;
	}
	public void setTraveldate(Date traveldate) {
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
