package com.biz.tour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.tour.domain.userwater.FishUserWaterPicsVO;
import com.biz.tour.domain.userwater.FishUserWaterVO;
import com.biz.tour.service.fileupload.FileUploadToServerService;
import com.biz.tour.service.userwater.UserWaterPicsService;
import com.biz.tour.service.userwater.UserWaterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/fishUser")
public class FishUserController {
	private final UserWaterService uWaterService;
	private final UserWaterPicsService uWPicsService;
	private final FileUploadToServerService fUploadService;
	
	@RequestMapping(value = "/waterInsert",method=RequestMethod.GET)
	public String insert(Model model) {
		FishUserWaterVO userVO=new FishUserWaterVO();
		model.addAttribute("userVO", userVO);
		return "fishing/userInput";
	}
	
	@RequestMapping(value = "/waterInsert",method=RequestMethod.POST)
	public String insert(FishUserWaterVO userVO,MultipartHttpServletRequest uploaded_files,Model model) {
		int ret=uWaterService.insert(userVO);
		
		//파일 업로드와 파일 이름을 DB에 저장을 같이함.
		fUploadService.filesUp(uploaded_files);
		return null;
	}
}
