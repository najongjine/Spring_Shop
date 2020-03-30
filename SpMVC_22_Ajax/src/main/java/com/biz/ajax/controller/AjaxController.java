package com.biz.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.ajax.domain.AddrVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/ajax")
public class AjaxController {
	@ResponseBody
	@RequestMapping(value = "",method=RequestMethod.GET)
	public String ajax_method() {
		return "OK";
	}
	
	/*
	 * 전자정부 관련하여 web service에서 사용하던 url pattern
	 * 일반적으로 서버에 처음 접속을 하면 index.html 라는 파일을 보여주고
	 * 메뉴를 클릭하거나 어떤 기능을 선택할때 접미사로 .do를 붙여서 연동하도록 하는 패턴
	 * *.do 접미사가 붙어있는 url은 컨트롤러를 통해서 업무를 처리하고 그렇지 않은 요청은 모두 무시.
	 * 
	 * project web.xml 의 appServlet url-pattern을 설정하고 대신 index.html, index.jsp 파일을 webapp
	 * 폴더에 작성해 두어야 한다.
	 * 그래야 프로젝트가 톰갯에 의해 run 되었을때 최초 404 오류가 나타나지 않는다.
	 */
	@ResponseBody
	@RequestMapping(value = "/msg.do",method=RequestMethod.GET)
	public String ajax_method(String msg) {
		if(msg.equalsIgnoreCase("KOREA")) {
			return "KOREA";
		}else {
			return "ERROR";
		}
	}
	
	/*
	 * 객체가 json으로 변환되어 클라이언트에 전송.
	 * jacksonbind 가 필요!!
	 */
	@ResponseBody
	@RequestMapping(value = "/addr",method=RequestMethod.GET)
	public AddrVO addr() {
		AddrVO vo=AddrVO.builder().ad_name("aaa").ad_addr("aaa addr").ad_age(16).ad_tel("010-aaa").build();
		return vo;
	}
	
	@RequestMapping(value = "/addr_view",method=RequestMethod.GET)
	public String addr(Model model) {
		AddrVO vo=AddrVO.builder().ad_name("aaa").ad_addr("aaa addr").ad_age(16).ad_tel("010-aaa").build();
		model.addAttribute("vo", vo);
		return "addr";
	}
	
	@ResponseBody
	@RequestMapping(value = "/put",method=RequestMethod.PUT,produces = "text/plain;charset=UTF-8")
	public String ajaxPut(String msg) {
		return msg;
	}
}
