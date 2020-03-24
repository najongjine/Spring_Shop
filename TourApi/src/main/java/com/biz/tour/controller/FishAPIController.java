package com.biz.tour.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.tour.domain.fishAreaBased.FishAreaBasedVO;
import com.biz.tour.domain.fishDetailCommon.FishDetailCommonVO;
import com.biz.tour.service.TourService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/fish")
public class FishAPIController {
	private final TourService tourService;
	
	@RequestMapping(value = "/water",method=RequestMethod.GET)
	public String waterFishing(Model model) {
		List<FishAreaBasedVO> fistList=tourService.getFishingAreaBased("areaBased","water");
		log.debug("리스트: "+fistList);
		model.addAttribute("fistList", fistList);
		return "fishing/fishingList";
	}
	
	@RequestMapping(value = "/sea",method=RequestMethod.GET)
	public String SeaFishing(Model model) {
		List<FishAreaBasedVO> fistList=tourService.getFishingAreaBased("areaBased","sea");
		model.addAttribute("fistList", fistList);
		return "fishing/fishingList";
	}
	
	@RequestMapping(value = "/detail",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public String detail(String contentid, Model model) {
		FishDetailCommonVO fishVO=tourService.getFishingDetail(contentid);
		model.addAttribute("fishVO", fishVO);
		return "fishing/fishDetail";
	}
	
}
