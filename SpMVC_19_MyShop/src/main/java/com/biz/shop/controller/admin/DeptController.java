package com.biz.shop.controller.admin;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.shop.domain.DeptVO;
import com.biz.shop.service.DeptService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@SessionAttributes("deptVO")
@RequestMapping(value = "/admin/dept")
public class DeptController {
	private final DeptService deptService;
	
	@ModelAttribute("deptVO")
	public DeptVO makeDeptVO() {
		return new DeptVO();
	}
	
	@RequestMapping(value = {"/",""},method=RequestMethod.GET)
	public String input(@ModelAttribute("deptVO") DeptVO deptVO,Model model) {
		deptVO=makeDeptVO();
		model.addAttribute("deptVO", deptVO);
		model.addAttribute("BODY", "DEPT");
		return "admin/main";
	}
	@RequestMapping(value ="/input",method=RequestMethod.POST)
	public String insert(@ModelAttribute("deptVO") @Valid DeptVO deptVO,BindingResult result
			,Model model,SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("BODY", "DEPT");
			return "admin/main";
		}
		deptService.save(deptVO);
		status.setComplete();
		return "redirect:/admin/dept";
	}
	
	@RequestMapping(value ="/update/{id}",method=RequestMethod.GET)
	public String update(@ModelAttribute("deptVO") @Valid DeptVO deptVO,BindingResult result
			,Model model,SessionStatus status,@PathVariable("id") String strId) {
		if(result.hasErrors()) {
			model.addAttribute("BODY", "DEPT");
			return "admin/main";
		}
		long id=Long.valueOf(strId);
		deptVO=deptService.findById(id);
		model.addAttribute("deptVO", deptVO);
		model.addAttribute("BODY", "DEPT");
		return "admin/main";
	}

}
