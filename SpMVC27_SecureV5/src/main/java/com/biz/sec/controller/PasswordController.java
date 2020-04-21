package com.biz.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.sec.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/password")
public class PasswordController {
	private final UserService userService;
	
	@RequestMapping(value = "/resetPass",method=RequestMethod.GET)
	public String resetPass(Model model) {
		return "password/resetPass";
	}
	
	/**
	 * 비밀번호를 초기화 시켜서 이메일로 초기화된 비밀번호를 발송해줌.
	 * @param username
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/resetPass",method=RequestMethod.POST)
	public String resetPass(String username,String email) {
		boolean bRet=userService.resetPassword(username, email);
		return "redirect:/";
	}
}
