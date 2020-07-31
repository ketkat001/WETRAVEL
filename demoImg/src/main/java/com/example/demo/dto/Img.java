package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

public class Img {
	private MultipartFile imgFile;

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
}
