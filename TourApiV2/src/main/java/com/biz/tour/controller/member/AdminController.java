package com.biz.tour.controller.member;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.tour.domain.member.UserDetailsVO;
import com.biz.tour.service.member.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {
	private final UserService userService;
	
	//@ResponseBody
	@RequestMapping(value = "",method=RequestMethod.GET)
	public String admin(Principal principal) {
		return "admin/admin_main";
	}
	
	@RequestMapping(value = "/user_list",method=RequestMethod.GET)
	public String userList(Model model) {
		List<UserDetailsVO> userList=userService.selectAll();
		model.addAttribute("userList", userList);
		return "admin/user_list";
	}
	
	@RequestMapping(value = "/user_detail_view/{username}",method=RequestMethod.GET)
	public String userDetailView(@PathVariable("username") String username,Model model) {
		UserDetailsVO userVO=userService.findByUserName(username);
		model.addAttribute("userVO", userVO);
		return "admin/user_detail_view";
	}
	
	@RequestMapping(value = "/user_detail_view",method=RequestMethod.POST)
	public String mypage(UserDetailsVO userVO,String[] auth,Principal principal,Model model) {
		int ret=userService.update(userVO, auth);
		return "redirect:/admin/user_detail_view/"+userVO.getUsername();
	}
}
