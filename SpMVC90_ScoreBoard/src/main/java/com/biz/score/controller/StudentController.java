package com.biz.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.score.domain.StudentVO;
import com.biz.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {
	private final StudentService studentService;
	
	@ModelAttribute("studentVO")
	public StudentVO makeStudentVO() {
		StudentVO studentVO=new StudentVO();
		studentVO.setSt_class(1);
		return studentVO;
	}
	
	@RequestMapping(value = "/allStudents",method=RequestMethod.GET)
	public String getAllStudents(Model model,@RequestParam(value = "mode",defaultValue = "normal") String mode) {
		log.debug("!!! mode "+mode);
		List<StudentVO> studentList=studentService.findAll();
		if(mode.equalsIgnoreCase("rank")) {
			studentList=studentService.sortByRank(studentList);
		}
		model.addAttribute("studentList", studentList);
		return "student/allStudents";
	}
	
	@RequestMapping(value = "/detail",method=RequestMethod.GET)
	public String getDetail(@RequestParam("st_num") String strStNum,Model model) {
		long st_num=-1;
		try {
			st_num=Long.valueOf(strStNum);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("getDetail long 변환중 오류");
		}
		StudentVO studentVO=studentService.findByStNum(st_num);
		model.addAttribute("studentVO", studentVO);
		return "student/detail";
	}
	
	@RequestMapping(value = "/insert",method=RequestMethod.GET)
	public String insert(@ModelAttribute("studentVO") StudentVO studentVO,Model model) {
		studentVO=makeStudentVO();
		model.addAttribute("studentVO", studentVO);
		return "student/input";
	}
	@RequestMapping(value = "/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute("studentVO") StudentVO studentVO,Model model,String dummy) {
		int ret=studentService.insert(studentVO);
		return "redirect:/student/allStudents";
	}
	
	@RequestMapping(value = "/update",method=RequestMethod.GET)
	public String update(@RequestParam("st_num") String strStNum, @ModelAttribute("studentVO") StudentVO studentVO, Model model) {
		long st_num=-1;
		try {
			st_num=Long.valueOf(strStNum);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("update long 변환중 오류");
		}
		studentVO=studentService.findByStNum(st_num);
		model.addAttribute("studentVO", studentVO);
		return "student/input";
	}
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("studentVO") StudentVO studentVO,Model model) {
		int ret=studentService.update(studentVO);
		return "redirect:/student/allStudents";
	}
	
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("st_num") String strStNum,Model model) {
		long st_num=-1;
		try {
			st_num=Long.valueOf(strStNum);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("delete long 변환중 오류");
		}
		int ret=studentService.delete(st_num);
		return "redirect:/student/allStudents";
	}
}
