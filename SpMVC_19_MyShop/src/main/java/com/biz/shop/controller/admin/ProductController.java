package com.biz.shop.controller.admin;

import java.util.List;

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

import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@SessionAttributes("productVO")
@RequestMapping(value = "/admin/product")
public class ProductController {
	private final ProductService proService;
	
	@ModelAttribute("productVO")
	public ProductVO makeProductVO() {
		return new ProductVO();
	}

	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
	public String product(@ModelAttribute("productVO") ProductVO productVO,Model model) {
		productVO = new ProductVO();

		List<ProductVO> proList = proService.selectAll();

		model.addAttribute("PRO_LIST", proList);
		model.addAttribute("productVO", productVO);
		model.addAttribute("BODY", "PRODUCT");
		return "admin/main";
	}

	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public String product(@Valid @ModelAttribute("productVO") ProductVO productVO, BindingResult result, Model model
			,SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("BODY", "PRODUCT");
			return "admin/main";
		}

		proService.save(productVO);
		status.setComplete();
		return "redirect:/admin/product";

	}
	
	@RequestMapping(value = "/update/{id}",method=RequestMethod.GET)
	public String update(@ModelAttribute("productVO") ProductVO productVO,@PathVariable("id") String strId,Model model) {
		List<ProductVO> proList = proService.selectAll();

		model.addAttribute("PRO_LIST", proList);
		
		long id=Long.valueOf(strId);
		productVO=proService.findById(id);
		model.addAttribute("productVO", productVO);
		model.addAttribute("BODY", "PRODUCT");
		return "admin/main";
	}
}
