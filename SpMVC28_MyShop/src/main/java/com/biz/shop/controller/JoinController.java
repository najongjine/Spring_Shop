package com.biz.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.shop.domain.UserDetailsVO;
import com.biz.shop.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SessionAttributes("userVO")
@Controller
@RequestMapping(value = "/join")
@RequiredArgsConstructor
@Slf4j
/**
 * Spring Security 기반 회원가입 및 email 인증 프로젝트 메인컨트롤러 
 *@since 2020-04-20
 *@author najongjine
 */
public class JoinController {
	private final UserService userSerive;
	
	@ModelAttribute("userVO")//sessionAtribute과 연동 
	public UserDetailsVO newUser() {
		return new UserDetailsVO();
	}
	
	/**
	 * 회원가입 화면 보여주기
	 *@since 2020-04-20
	 *
	 *sessionAttribute 활용
	 *localhost/sec/join: 회원가입 화면 보이기
	 *join/user: 회원가입 버튼 클릭 후
	 *join/email_ok: email 인증 화면에서 email 보내기 후 
	 * UPDATE: 2020-04-21
	 *localhost/sec/join:회원가입 화면 보이기
	 *join/join_next: 회원가입 버튼 클릭후 DB에 회원정보를 보여준 후 email 인증화면 보이기
	 *join/join_last: email 인증후 이후 처리 
	 */
	@RequestMapping(value = "",method=RequestMethod.GET)
	public String join(@ModelAttribute("userVO") UserDetailsVO userVO,Model model) {//jsp form과 연동
		return "join/join";
	}
	
	
	/**
	 *@since 2020-04-20
	 *회원가입에서 username,password 입력후 email 보내기 화면으로 이동하기 
	 */
	@RequestMapping(value = "/joinok",method=RequestMethod.POST)
	public String joinok(@ModelAttribute("userVO") UserDetailsVO userVO,SessionStatus session,
			Model model) {//jsp form과 연동
		int ret=userSerive.insert(userVO);
		model.addAttribute("JOIN", "EMAIL_OK");
		//session.setComplete();//sessionattribute clear 시키기
		return "join/join_email";
	}
	
	
}
