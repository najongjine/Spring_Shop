package com.biz.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.score.domain.ScoreVO;
import com.biz.score.service.ScoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/score")
@Slf4j
public class ScoreController {
	private final ScoreService scoreService;
	
	@ModelAttribute("scoreVO")
	public ScoreVO makeScoreVO() {
		return new ScoreVO();
	}
	
	@RequestMapping(value = "/getAllScores",method=RequestMethod.GET)
	public String getAllScores() {
		List<ScoreVO> scoreVO=scoreService.findAll();
		return null;
	}
	
	@RequestMapping(value = "/findBySNum",method=RequestMethod.GET)
	public String getSocreBySNum(@RequestParam("s_num") String strSNum,Model model) {
		long s_num=-1;
		try {
			s_num=Long.valueOf(strSNum);
		} catch (Exception e) {
			log.debug("findBySNum long 변환중 에러");
		}
		List<ScoreVO> scoreList=scoreService.findBySNum(s_num);
		model.addAttribute("scoreList", scoreList);
		return null;
	}
	
	@RequestMapping(value = "/insert",method=RequestMethod.GET)
	public String insert(@ModelAttribute("scoreVO") ScoreVO scoreVO,@RequestParam("s_num") String strSNum,Model model) {
		long s_num=-1;
		try {
			s_num=Long.valueOf(strSNum);
		} catch (Exception e) {
			log.debug("findBySNum long 변환중 에러");
		}
		if(s_num<0) {
			return null;
		}
		scoreVO=makeScoreVO();
		scoreVO.setS_num(s_num);
		model.addAttribute("scoreVO", scoreVO);
		return "score/input";
	}
	@RequestMapping(value = "/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute("scoreVO") ScoreVO scoreVO,Model model) {
		int ret=scoreService.insert(scoreVO);
		return "redirect:/student/allStudents";
	}
	
	@RequestMapping(value = "/update",method=RequestMethod.GET)
	public String update(@ModelAttribute("scoreVO") ScoreVO scoreVO,Model model) {
		long s_id=-1;
		try {
			s_id=Long.valueOf(scoreVO.getS_id());
		} catch (Exception e) {
			log.debug("findBySNum long 변환중 에러");
		}
		if(s_id<0) {
			log.debug("!!! sid: "+s_id);
			return null;
		}
		scoreVO=scoreService.findById(s_id);
		model.addAttribute("scoreVO", scoreVO);
		return "score/input";
	}
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("scoreVO") ScoreVO scoreVO,Model model,String dummy) {
		int ret=scoreService.update(scoreVO);
		return "redirect:/student/allStudents";
	}
	
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("s_id") String strSId,Model model) {
		long s_id=-1;
		try {
			s_id=Long.valueOf(strSId);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("delete long 변환중 에러");
		}
		if(s_id<0) {
			log.debug("!!! sid "+s_id);
			return null;
		}
		int ret=scoreService.delete(s_id);
		return "redirect:/student/allStudents";
	}
}
