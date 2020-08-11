package com.ssafy.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.travel.service.SearchService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/search")
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@ApiOperation(value = "특정 province의 city 목록을 return한다. ", response = List.class)
	@GetMapping("{province}")
	public ResponseEntity<List<String>> getCityList(@PathVariable String province) {
		return new ResponseEntity<List<String>>(searchService.getCityList(province), HttpStatus.OK);
	}
}
