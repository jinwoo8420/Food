package com.food.app.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.food.app.model.WeatherVO;
import com.food.app.service.DustService;
import com.food.app.service.WeatherService;

@Controller
@RequestMapping(value = "/detail")
public class DetailController {

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private DustService dustService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String home() {

		return "detail/list";
	}
	
	@RequestMapping(value = "/{mapx},{mapy}/list")
	public String list(@PathVariable("mapx") String mapx, @PathVariable("mapy") String mapy, Model model)
			throws IOException, ParseException {

		List<WeatherVO> weather = weatherService.getWeather(mapx, mapy);

		model.addAttribute("WEATHER", weather);

		return "detail/list";
	}

}
