package com.food.app.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.food.app.model.DustVO;
import com.food.app.model.WeatherVO;
import com.food.app.service.AllFoodService;
import com.food.app.service.DustService;
import com.food.app.service.NaverService;
import com.food.app.service.WeatherService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/detail")
public class DetailController {

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private DustService dustService;

	@Autowired
	private AllFoodService foodService;

	@Autowired
	private NaverService naverService;

	@GetMapping("/error")
	public String error() {
		return "error/error";
	}

	@RequestMapping(value = "/{mapx},{mapy},{dust}/list")
	public String list(@PathVariable("mapx") String mapx, @PathVariable("mapy") String mapy,
			@PathVariable("dust") String dust, Model model) throws IOException, ParseException {

		String allFood = foodService.getFood();

		// 날씨
		List<WeatherVO> weather = weatherService.getWeather(mapx, mapy);
		String rain_queryString = naverService.queryString(dust, weather.get(6).getRainFood());
		List<Object> naverRainList = naverService.getNaver(rain_queryString);

		// 미세먼지
		List<DustVO> getDust = dustService.getDust(dust);
		String dust_queryString = naverService.queryString(dust, getDust.get(0).getDustFood());
		List<Object> naverDustList = naverService.getNaver(dust_queryString);

		// 전체 음식 리스트
		String food_queryString = naverService.queryString(dust, allFood);
		List<Object> naverFoodList = naverService.getNaver(food_queryString);

		// 비 올 때
		if (weather.get(6).getPtyValue().equals("비")) {
			model.addAttribute("LIST", naverRainList);
			model.addAttribute("FOOD", weather.get(6).getRainFood());
		}		
		// 미세먼지 나쁨 이상
		else if (getDust.get(0).getPm10Grade().equals("나쁨") || getDust.get(0).getPm10Grade().equals("매우 나쁨")) {
			model.addAttribute("LIST", naverDustList);
			model.addAttribute("FOOD", getDust.get(0).getDustFood());
		} else if (getDust.get(0).getPm25Grade().equals("나쁨") || getDust.get(0).getPm25Grade().equals("매우 나쁨")) {
			model.addAttribute("LIST", naverDustList);
			model.addAttribute("FOOD", getDust.get(0).getDustFood());
		} else {
			model.addAttribute("LIST", naverFoodList);
			model.addAttribute("FOOD", allFood);
		}

		model.addAttribute("WEATHER", weather);
		model.addAttribute("DUST", getDust);

		return "detail/list";
	}

}