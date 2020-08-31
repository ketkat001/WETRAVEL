package com.ssafy.travel.controller;

import java.nio.charset.Charset;
import java.sql.Blob;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.travel.dto.Article;
import com.ssafy.travel.dto.Score;
import com.ssafy.travel.service.ArticleService;

import io.swagger.annotations.ApiOperation;

// http://localhost:8999/travel/swagger-ui.html
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/article")
public class ArticleController {
	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ArticleService articleService;
	
	@ApiOperation(value = "Book번호에 해당하는 모든 article의 정보를 반환한다.", response = List.class)
	@GetMapping("all/{bookno}")
	public ResponseEntity<List<Article>> getArticleList(@PathVariable int bookno) throws Exception {
		logger.debug("getArticleList - 호출");
		return new ResponseEntity<List<Article>>(articleService.getArticleList(bookno), HttpStatus.OK);
	}

    @ApiOperation(value = "article번호에 해당하는 article의 정보를 반환한다.", response = Article.class)    
	@GetMapping("{articleno}")
	public ResponseEntity<Article> getArticleDetail(@PathVariable int articleno) {
		logger.debug("getArticleDetail - 호출");
		return new ResponseEntity<Article>(articleService.getArticleDetail(articleno), HttpStatus.OK);
	}

    @ApiOperation(value = "새로운 article 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("article")
	public ResponseEntity<String> registArticle(@ModelAttribute Article article) {
		logger.debug("registArticle - 호출");
		try {
			System.out.println(article.getExiflat());
			System.out.println(article.getExiflong());
			article.setImg();
			article.setImgName();
			if (articleService.registArticle(article)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "article번호에 해당하는 article의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{articleno}")
	public ResponseEntity<String> modifyArticle(@ModelAttribute Article article) {
		logger.debug("modifyArticle - 호출");
		logger.debug("" + article);

		article.setImg();
		article.setImgName();
		if (articleService.modifyArticle(article)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "article번호에 해당하는 article의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{articleno}")
	public ResponseEntity<String> deleteArticle(@PathVariable int articleno) {
		logger.debug("deleteArticle - 호출");
		if (articleService.deleteArticle(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    
    @ApiOperation(value = "새로운 score 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("score")
	public ResponseEntity<String> registScore(@RequestBody Score score) {
		logger.debug("registScore - 호출");
		if (articleService.registScore(score)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
