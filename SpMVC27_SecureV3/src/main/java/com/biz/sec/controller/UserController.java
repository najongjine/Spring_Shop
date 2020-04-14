package com.biz.sec.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.service.UserService;
import com.biz.sec.service.auth.UserDetailsServiceImp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final UserDetailsServiceImp userDService;
	
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
		boolean ret=userService.isExistsUserName(username);
		if(ret) {
			return "EXISTS";
		}
		return "NONEXISTS";
	}
	@ResponseBody
	@RequestMapping(value = "",method=RequestMethod.GET)
	public String user() {
		return "user HOME";
	}
	
	@RequestMapping(value = "/mypage",method=RequestMethod.GET)
	public String mypage(Principal principal,Model model) {
		UserDetailsVO userVO=(UserDetailsVO) userDService.loadUserByUsername(principal.getName());
		model.addAttribute("userVO", userVO);
		return "user/view";
	}
	
	@RequestMapping(value = "/update",method=RequestMethod.GET)
	public String update(String username,Model model) {
		UserDetailsVO userVO=(UserDetailsVO) userDService.loadUserByUsername(username);
		model.addAttribute("userVO", userVO);
		return "user/input";
	}
	
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	public String update(UserDetailsVO userVO,Model model) {
		int ret=userService.update(userVO);
		model.addAttribute("userVO", userVO);
		return "redirect:/";
	}
}
