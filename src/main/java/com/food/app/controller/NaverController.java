package com.food.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.food.app.service.NaverService;

@Controller
@RequestMapping(value = "/test")
public class NaverController {
	
	@Autowired
	private NaverService naverService;
	
	@RequestMapping(value = "/naver")
	public String home(Model model) throws UnsupportedEncodingException {
		int i = (int) (Math.random() * 12);

		String[] rain_food = { "부대찌개", "아구찜", "해물탕", "칼국수", "수제비", "짬뽕", "우동", "치킨", "국밥", "김치전", "두부김치", "파전" };
		
		String queryString = naverService.queryString(rain_food[i]);
		
		List<Object> naverList = naverService.getNaver(queryString);
		
		model.addAttribute("NAVER", naverList);
		model.addAttribute("FOOD", rain_food[i]);
		
		return "test/naver/home";
	}

}
