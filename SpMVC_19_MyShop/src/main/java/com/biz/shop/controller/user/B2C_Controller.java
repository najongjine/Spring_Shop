package com.biz.shop.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/user/product")
public class B2C_Controller {
	private final ProductService prodService;
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String list(Model model) {
		List<ProductVO> prodList=prodService.selectAll();
		model.addAttribute("prodList", prodList);
		return "users/user_product_list";
	}
	
	@RequestMapping(value = "/detail",method=RequestMethod.GET)
	public String detail(Model model,ProductVO prodVO) {
		prodVO=prodService.findById(prodVO.getId());
		model.addAttribute("prodVO", prodVO);
		return "users/user_product_detail";
	}

}
