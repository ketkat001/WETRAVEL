package com.ssafy.travel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.travel.dto.Book;
import com.ssafy.travel.dto.User;
import com.ssafy.travel.service.JwtService;
import com.ssafy.travel.service.UserService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8999/travel/swagger-ui.html
@RestController
@RequestMapping("/api/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtService jwtService;

    @ApiOperation(value = "입력한 이메일이 존재하면 true, 없으면 false를 반환한다", response = boolean.class)    
	@GetMapping("emailCheck")
    public boolean emailCheck(@RequestParam("email") String email) {
    	User userIdCheck = userService.getUserOne(email, "email");
    	if (userIdCheck != null)
    		return true;
    	else
    		return false;
    }

    @ApiOperation(value = "입력한 닉네임이 존재하면 true, 없으면 false를 반환한다", response = boolean.class)    
	@GetMapping("nicknameCheck")
    public boolean nicknameCheck(@RequestParam("nickname") String nickname) {
    	User userNicknameCheck = userService.getUserOne(nickname, "nickname");
    	if (userNicknameCheck != null)
    		return true;
    	else
    		return false;
    }
	
	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user, HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			User userIdCheck = userService.getLogin(user.getEmail(), user.getPassword());
			if (userIdCheck == null)
				throw new RuntimeException();
			String token = jwtService.create(userIdCheck);
			res.setHeader("jwt-auth-token", token);
			resultMap.put("status", true);
			resultMap.put("data", userIdCheck);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("signUp")
	@ResponseBody
	public int signUp(@RequestBody User user) {
		int result = 0;
		
		if (emailCheck(user.getEmail()))
			result = 2;
		
		if (nicknameCheck(user.getNickname()))
			result = 3;
		
		if (result < 2)
			result = userService.userJoin(user);
		
		return result;
	}
	
    @ApiOperation(value = "token에 저장된 user의 정보를 반환한다.", response = User.class)    
	@GetMapping("{jwt}")
	public ResponseEntity<User> getUserDetail(@PathVariable String jwt) {
		logger.debug("getUserDetail - 호출");
		return new ResponseEntity<User>(jwtService.getUser(jwt), HttpStatus.OK);
	}
    
    @ApiOperation(value = "email과 일치하는 user의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{email}")
	public ResponseEntity<String> modifyUser(@RequestBody User user) {
		logger.debug("modifyUser - 호출");
		logger.debug("" + user);
		
		if (userService.modifyUser(user)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "email과 일치하는 user의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{email}")
	public ResponseEntity<String> deleteUser(@PathVariable String email) {
		logger.debug("deleteUser - 호출");
		if (userService.deleteUser(email)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
