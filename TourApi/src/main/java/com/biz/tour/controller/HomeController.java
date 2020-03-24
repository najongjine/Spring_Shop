package com.biz.tour.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.tour.domain.fishAreaBased.FishAreaBasedVO;
import com.biz.tour.service.TourService;

import lombok.RequiredArgsConstructor;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequiredArgsConstructor
public class HomeController {
	private final TourService tourService;
	
	//,produces = "text/json;charset=UTF-8"
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws URISyntaxException {
		
		 return "fishing/fishMain";
	}
	
}
