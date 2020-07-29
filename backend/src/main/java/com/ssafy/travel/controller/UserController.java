package com.ssafy.travel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.travel.dto.Book;
import com.ssafy.travel.dto.User;
import com.ssafy.travel.service.UserService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8999/travel/swagger-ui.html
@RestController
@RequestMapping("api/user/*")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	UserService userService;
	
	@PostMapping("api/user/login")
	@ResponseBody
	public User login(@RequestBody User user, HttpSession session) {
		String col = "email";
		User userIdCheck = userService.getUserOne(user.getEmail(), col);
		User errorState = new User("No Match", "No Match", "No Match", "No Match");
		if (userIdCheck == null)
			return userIdCheck;
		else {
			if (user.getEmail().equals(userIdCheck.getEmail())) {
				if (user.getPassword().equals(userIdCheck.getPassword())) {
					session.setAttribute("loginUser", userIdCheck);
					return userIdCheck;
				}
				else
					return errorState;
			}
			else
				return errorState;
		}
	}
	
	@PostMapping("api/user/signUp")
	@ResponseBody
	public int signUp(@RequestBody User user) {
		int result = 0;
		String col = null;
		
		col = "email";
		User userIdCheck = userService.getUserOne(user.getEmail(), col);
		if (userIdCheck != null)
			result = 2;
		
		col = "nickname";
		User userNicknameCheck = userService.getUserOne(user.getNickname(), col);
		if (userNicknameCheck != null)
			result = 3;
		
		if (result < 2)
			result = userService.userJoin(user);
		
		return result;
	}
	
    @ApiOperation(value = "email에 해당하는 user의 정보를 반환한다.", response = User.class)    
	@GetMapping("{email}")
	public ResponseEntity<User> getUserDetail(@PathVariable String email) {
		logger.debug("getUserDetail - 호출");
		return new ResponseEntity<User>(userService.getUserDetail(email), HttpStatus.OK);
	}
    
    @ApiOperation(value = "email에 해당하는 user의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{email}")
	public ResponseEntity<String> modifyUser(@RequestBody User user) {
		logger.debug("modifyUser - 호출");
		logger.debug("" + user);
		
		if (userService.modifyUser(user)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "email에 해당하는 user의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{email}")
	public ResponseEntity<String> deleteUser(@PathVariable String email) {
		logger.debug("deleteUser - 호출");
		if (userService.deleteUser(email)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
