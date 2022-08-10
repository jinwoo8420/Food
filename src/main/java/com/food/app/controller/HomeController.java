package com.food.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.food.app.model.WeatherVO;
import com.food.app.service.WeatherService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) throws IOException {
		List<WeatherVO> weather = weatherService.getWeather("60", "74");

		log.debug("테스트 {}", weather);

		model.addAttribute("TEST", weather);

		return "home";
	}

}
