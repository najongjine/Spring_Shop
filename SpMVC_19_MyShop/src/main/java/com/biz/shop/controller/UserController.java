package com.biz.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@RequestMapping(value = "/user")
@Controller
@RequiredArgsConstructor
public class UserController {
	
	@RequestMapping(value = {"/",""},method=RequestMethod.GET)
	public String user() {
		return "home";
	}
}
