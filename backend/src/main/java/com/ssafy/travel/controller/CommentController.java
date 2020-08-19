package com.ssafy.travel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.travel.dto.Comment;
import com.ssafy.travel.service.CommentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private CommentService commentService;
	
	@ApiOperation(value = "새로운 댓글을 입력하고 성공 여부에 따라 success 또는 fail을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registComment(@RequestBody Comment comment) {
		if (commentService.registComment(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "article 번호에 해당하는 article에 등록된 댓글 목록을 반환한다. ", response = List.class)
	@GetMapping("{articleno}")
	public ResponseEntity<List<Comment>> getCommentList(@PathVariable int articleno) {
		return new ResponseEntity<List<Comment>>(commentService.getCommentList(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "comment 번호에 해당하는 댓글의 정보를 수정한다. ", response = String.class)
	@PutMapping("{commentno}")
	public ResponseEntity<String> modifyComment(@RequestBody Comment comment) {
		if (commentService.modifyComment(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "comment 번호에 해당하는 댓글의 정보를 DB에서 삭제한다. ", response = String.class)
	@DeleteMapping("{commentno}")
	public ResponseEntity<String> deleteComment(@PathVariable int commentno) {
		if (commentService.deleteComment(commentno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
