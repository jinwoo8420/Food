package com.food.app.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.food.app.model.DustVO;
import com.food.app.model.WeatherVO;
import com.food.app.service.DustService;
import com.food.app.service.NaverService;
import com.food.app.service.WeatherService;

@Controller
@RequestMapping(value = "/detail")
public class DetailController {

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private DustService dustService;

	@Autowired
	private NaverService naverService;

	@RequestMapping(value = "/{mapx},{mapy},{dust}/list")
	public String list(@PathVariable("mapx") String mapx, @PathVariable("mapy") String mapy,
			@PathVariable("dust") String dust, Model model) throws IOException, ParseException {

		List<WeatherVO> weather = weatherService.getWeather(mapx, mapy);
		List<DustVO> getDust = dustService.getDust(dust);

		int i = (int) (Math.random() * 12);
		int j = (int) (Math.random() * 5);

		String[] rain_food = { "부대찌개", "아구찜", "해물탕", "칼국수", "수제비", "짬뽕", "우동", "치킨", "국밥", "김치전", "두부김치", "파전" };
		String[] dust_food = { "콩나물국밥", "고등어", "굴", "쌀국수", "마라탕" };

		String rain_queryString = naverService.queryString(rain_food[i]);
		String dust_queryString = naverService.queryString(dust_food[j]);
		
		List<Object> naverRainList = naverService.getNaver(rain_queryString);
		List<Object> naverDustList = naverService.getNaver(dust_queryString);

		model.addAttribute("WEATHER", weather);
		model.addAttribute("DUST", getDust);
		
		model.addAttribute("RAINLIST", naverRainList);
		model.addAttribute("RAINFOOD", rain_food[i]);
		
		model.addAttribute("DUSTLIST", naverDustList);
		model.addAttribute("DUSTFOOD", dust_food[j]);

		return "detail/list";
	}

}
