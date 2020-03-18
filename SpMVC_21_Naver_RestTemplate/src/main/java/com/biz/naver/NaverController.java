package com.biz.naver;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.naver.domain.NaverVO;
import com.biz.naver.service.NaverService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/naver")
@RequiredArgsConstructor
public class NaverController {
	private final NaverService nService;
	
	@ResponseBody
	@RequestMapping(value = "/search",method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public List<NaverVO> naverSearch(String cat,String search) {
		List<NaverVO> ret=nService.naverSearch(cat,search);
		return ret;
	}
}
