package com.biz.bbs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.service.BBsService;
import com.biz.bbs.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
/*
 * class에 부착하는 mapping
 * type수준의 req...
 * top level req...
 * 메서드에 /list라고 reqmap을 붙이면 호출을 할때 context/comment/list 라고 path를 지정한다.
 */
@RequestMapping(value = "/comment")
public class CommentController {
	private final CommentService cmtService;
	private final BBsService bbsService;
	
	/*
	 * 게시판의 ID값을 매개변수로 받아서 코멘트 리스트를 보여주는 메서드
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String list(String b_id,Model model){
		long c_b_id=Long.valueOf(b_id);
		List<CommentVO> cmtList=cmtService.findByBId(c_b_id);
		model.addAttribute("CMT_LIST", cmtList);
		return "comment_list";
	}
	
	/*
	 * 코멘트 입력값을 매개변수로 받아서 db insert를 수행할 메서드
	 */
	@RequestMapping(value = "/insert",method=RequestMethod.POST)
	public String insert(CommentVO cmtVO,Model model) {
		cmtService.insert(cmtVO);
		
		/*
		 * redirect로 방향전환을 할때 변수에 값을 던달하고 싶으면
		 * 일반적인 방법 ?변수=값&변수1=값 형식으로 문자열을 연결연산을 수행해야 하는데
		 * model attribute로 해당값을 add 수행한후 redirect를 수행하면
		 * 쿼리 문자열을 자동으로 만들어서 전달한다.
		 */
		model.addAttribute("b_id", cmtVO.getC_b_id());
		model.addAttribute("writer", cmtVO.getC_writer());
		//return "redirect:/detail";
		return "redirect:/comment/list";
		/*일잔적인 방법
		 * long c_b_id=cmtVO.getC_b_id(); 
		 * return "redirect:/detail?b_id="+c_b_id;
		 */
	}
	
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public String delete(String c_id,String b_id,Model model) {
		int ret=cmtService.delete(Long.valueOf(c_id));
		model.addAttribute("b_id", b_id);
		return "redirect:/comment/list";
	}
}
