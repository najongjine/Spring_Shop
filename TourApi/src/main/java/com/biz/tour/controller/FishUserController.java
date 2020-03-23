package com.biz.tour.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.tour.domain.fishAreaBased.FishAreaBasedVO;
import com.biz.tour.domain.fishDetailCommon.FishDetailCommonVO;
import com.biz.tour.service.TourService;
import com.biz.tour.service.fileupload.FileUploadToServerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/fish")
public class FishUserController {
	private final TourService tourService;
	private final FileUploadToServerService fUploadService;
	
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
	
	@ResponseBody
	@RequestMapping(value = "/detail",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public FishDetailCommonVO detail(String contentid, Model model) {
		return tourService.getFishingDetail(contentid);
	}
	
	@RequestMapping(value = "/insert",method=RequestMethod.POST)
	public String insert(MultipartHttpServletRequest uploaded_files) {
		fUploadService.filesUp(uploaded_files);
		return null;
	}
}
