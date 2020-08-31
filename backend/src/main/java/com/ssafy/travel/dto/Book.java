package com.ssafy.travel.dto;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book implements Serializable {
	private int bookno;
	private String title;
	private String writer;
	private Date writedate;
	private String province;
	private String city;
	private String startdate;
	private String description;
	private byte[] img;
	
	@JsonIgnore
	private MultipartFile thumbnail;
	
	@JsonProperty("thumbnail")
	private String imgName;

	private double score;
	
	public Book() {
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

	public Book(int bookno, String title, String writer, Date writedate, String province, String city,
			String startdate, String description, double score, MultipartFile thumbnail) {
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
		this.thumbnail = thumbnail;
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
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
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
