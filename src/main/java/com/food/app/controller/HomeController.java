package com.food.app.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.food.app.model.DustVO;
import com.food.app.model.WeatherVO;
import com.food.app.service.DustService;
import com.food.app.service.WeatherService;

@Controller
public class HomeController {

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private DustService dustService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		return "home";
	}

	@RequestMapping(value = "/intro", method = RequestMethod.GET)
	public String intro() {

		return "intro";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {

		return "test/test";
	}

	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public String weather(Model model) throws IOException, ParseException {
		List<WeatherVO> weather = weatherService.getWeather("60", "74");

		model.addAttribute("TEST", weather);

		return "test/weather/list";
	}

	@RequestMapping(value = "/dust", method = RequestMethod.GET)
	public String dust(Model model) throws IOException {
		List<DustVO> dust = dustService.getDust("서울");

		model.addAttribute("TEST", dust);

		return "test/dust/list";
	}
	
	@RequestMapping(value = "/test/kakao", method = RequestMethod.GET)
	public String kakao() {

		return "test/kakao/home";
	}


}
