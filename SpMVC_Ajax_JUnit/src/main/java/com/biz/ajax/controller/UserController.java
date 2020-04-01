package com.biz.ajax.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.ajax.domain.UserVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
	
	/*
	 * @modelattribute form에서 전송되는 데이터를 VO에 한꺼번에 받기위해서 사용하는 annotation
	 * Sp4 에서부터는 선택사항.
	 * 
	 * form의 input box name으로 설정된 이름의 변수가 VO에 있으면 알아서 잘 매칭되어 데이터를 받을수 있다.
	 * 
	 * form에서 받은 데이터를 다시 view로 재 전송할때 Model에 데이터를 싣지 않아도 자동으로 값이 실려서
	 * 전송된다.
	 */
	@RequestMapping(value = "saveUser",method=RequestMethod.POST)
	public String saveUser(@ModelAttribute("userVO") UserVO userVO) {
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value = "sendUserId",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public Map<String, Object> sendUserId(UserVO userVO) {
		Map<String, Object> msg=new HashMap<String, Object>();
		msg.put("RET_CODE", "RECV_OK");
		msg.put("userVO", userVO);
		//return userVO.getUserId();
		//return "home";
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "sendUser",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public UserVO sendUser(UserVO userVO) {
		return userVO;
	}
	
	@RequestMapping(value = "html",method=RequestMethod.GET)
	public String sendUserFromHtml(UserVO userVO) {
		return "user_info";
	}
}
