package com.ssafy.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.travel.service.ArticleService;

@RestController
public class ArticleController {
	@Autowired
	private ArticleService service;
	
	
}
