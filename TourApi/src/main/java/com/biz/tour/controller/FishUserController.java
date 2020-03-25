package com.biz.tour.controller;

import java.util.List;

import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.tour.dao.userwater.FishWaterCommentDao;
import com.biz.tour.domain.userwater.FishUserWaterCommentVO;
import com.biz.tour.domain.userwater.FishUserWaterPicsVO;
import com.biz.tour.domain.userwater.FishUserWaterVO;
import com.biz.tour.service.fileupload.FileUploadToServerService;
import com.biz.tour.service.userwater.UserWaterCommentService;
import com.biz.tour.service.userwater.UserWaterPicsService;
import com.biz.tour.service.userwater.UserWaterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/fishUserWater")
public class FishUserController {
	private final UserWaterService uWaterService;
	private final UserWaterPicsService uWPicsService;
	private final FileUploadToServerService fUploadService;
	private final UserWaterCommentService uWCommentService;
	
	@RequestMapping(value = "/waterInsert",method=RequestMethod.GET)
	public String insert(Model model) {
		FishUserWaterVO userVO=new FishUserWaterVO();
		model.addAttribute("userVO", userVO);
		model.addAttribute("MODE", "insert");
		return "fishing/userInput";
	}
	
	@RequestMapping(value = "/waterInsert",method=RequestMethod.POST)
	public String insert(FishUserWaterVO userVO,MultipartHttpServletRequest uploaded_files,Model model) {
		int ret=uWaterService.insert(userVO);
		
		//파일 업로드와 파일 이름을 DB에 저장을 같이함.
		fUploadService.filesUp(uploaded_files,"water");
		return null;
	}
	
	@RequestMapping(value = "/waterUpdate",method=RequestMethod.GET)
	public String update(Model model,String strId) {
		Long uf_id=(long) -1;
		Long fk=(long) -1;
		try {
			uf_id=Long.valueOf(strId);
			fk=uf_id;
		} catch (Exception e) {
			log.debug("문자열 변환중 오류: "+uf_id);
		}
		
		FishUserWaterVO userVO=uWaterService.findById(uf_id);
		List<FishUserWaterPicsVO> picsList=uWPicsService.findByFK(fk);
		model.addAttribute("userVO", userVO);
		model.addAttribute("picsList", picsList);
		model.addAttribute("MODE", "update");
		return "fishing/userInput";
	}
	
	@RequestMapping(value = "/waterUpdate",method=RequestMethod.POST)
	public String update(FishUserWaterVO userVO,MultipartHttpServletRequest uploaded_files,Model model, String strId) {
		Long uf_id=(long) -1;
		try {
			uf_id=Long.valueOf(strId);
		} catch (Exception e) {
			log.debug("문자열 변환중 오류: "+uf_id);
		}
		
		userVO.setUf_id(uf_id);
		uWaterService.update(userVO);
		fUploadService.filesUp(uploaded_files,"water");
		return null;
	}
	
	@RequestMapping(value = "/deletePic",method=RequestMethod.GET)
	public String deletePic(String strUfp_id, String strFk,Model model) {
		Long ufp_id=(long) -1;
		Long fk=(long) -1;
		try {
			ufp_id=Long.valueOf(strUfp_id);
			fk=Long.valueOf(strFk);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("문자열 변환중 오류: "+fk);
			log.debug("문자열 변환중 오류: "+ufp_id);
		}
		int ret=uWPicsService.deleteById(ufp_id);
		model.addAttribute("strId", fk);
		return "redirect:/fishUser/waterUpdate";
	}
	
	// FishUserWaterVO 글 삭제 + sub table 들 cascade delete 구현필요
	
	@RequestMapping(value = "/view",method=RequestMethod.GET)
	public String view(@RequestParam("uf_id") String strUf_id,Model model) {
		long uf_id=-1;
		try {
			uf_id=Long.valueOf(strUf_id);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("view 문자열 변환중 오류: "+uf_id);
		}
		FishUserWaterVO userVO=uWaterService.findById(uf_id);
		List<FishUserWaterPicsVO> picsList=uWPicsService.findByFK(uf_id);
		model.addAttribute("userVO", userVO);
		model.addAttribute("picsList", picsList);
		
		return "fishing/userView";
	}
	
	@RequestMapping(value = "/comments",method=RequestMethod.GET)
	public String comments(@RequestParam("ufc_fk") String strFk,Model model) {
		long ufc_fk=-1;
		try {
			ufc_fk=Long.valueOf(strFk);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("cmt 문자열 변환중 오류: "+ufc_fk);
		}
		List<FishUserWaterCommentVO> commentList=uWCommentService.findByFk(ufc_fk);
		model.addAttribute("commentList", commentList);
		return "fishing/userComment";
	}
	
	@RequestMapping(value = "/comments",method=RequestMethod.POST)
	public String comments(FishUserWaterCommentVO commentVO,Model model) {
		int ret=uWCommentService.insert(commentVO);
		List<FishUserWaterCommentVO> commentList=uWCommentService.findByFk(commentVO.getUfc_fk());
		model.addAttribute("commentList", commentList);
		return "fishing/userComment";
	}
}
