package com.biz.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/nation")
@Controller
public class NationController {
	
	@ResponseBody
	@RequestMapping("/where")
	public String where() {
		String nation="해하다다\n";
		nation+="Korea\n";
		nation+="dnfleksk";
		
		return nation;
	}
	
	@ResponseBody
	@RequestMapping(value = "/korea",produces = "text/html;charset=UTF-8")
	public String korea() {
		String nation="해하다다\n";
		nation+="Korea\n";
		nation+="dnfleksk";
		
		return nation;
	}
}
