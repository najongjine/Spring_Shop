package com.biz.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.sec.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String login() {
		return "auth/login";
	}
	
	@RequestMapping(value = "/join",method=RequestMethod.GET)
	public String join() {
		return "auth/join";
	}
	
	@ResponseBody
	@RequestMapping(value = "/join",method=RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String join(String username, String password) {
		int ret=userService.insert(username, password);
		//return "redirect:/";
		return String.format("아이디: %s, 비번: %s", username,password);
	}

	@ResponseBody
	@RequestMapping(value = "/idcheck",method=RequestMethod.GET)
	public String idcheck(String username) {
		boolean ret=userService.isExistsId(username);
		if(ret) {
			return "USE";
		}
		return "YES";
	}
	@ResponseBody
	@RequestMapping(value = "",method=RequestMethod.GET)
	public String user() {
		return "user HOME";
	}
	
	@ResponseBody
	@RequestMapping(value = "/mypage",method=RequestMethod.GET)
	public String mypage() {
		return "user mypage";
	}
}
