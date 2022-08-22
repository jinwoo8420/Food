package com.food.app.controller;

import java.io.IOException;
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
	private NaverService naverService;

	@RequestMapping(value = "/{mapx},{mapy},{dust}/list")
	public String list(@PathVariable("mapx") String mapx, @PathVariable("mapy") String mapy,
			@PathVariable("dust") String dust, Model model) throws IOException, ParseException {

		List<WeatherVO> weather = weatherService.getWeather(mapx, mapy);
		List<DustVO> getDust = dustService.getDust(dust);

		int i = (int) (Math.random() * 12);
		int j = (int) (Math.random() * 5);
		int f = (int) (Math.random() * 17);

		String[] rain_food = { "부대찌개", "아구찜", "해물탕", "칼국수", "수제비", "짬뽕", "우동", "치킨", "국밥", "김치전", "두부김치", "파전" };
		String[] dust_food = { "콩나물국밥", "고등어", "굴", "쌀국수", "마라탕" };
		String[] food = { "부대찌개", "아구찜", "해물탕", "칼국수", "수제비", "짬뽕", "우동", "치킨", "국밥", "김치전", "두부김치", "파전", "콩나물국밥",
				"고등어", "굴", "쌀국수", "마라탕" };

		String rain_queryString = naverService.queryString(rain_food[i]);
		String dust_queryString = naverService.queryString(dust_food[j]);
		String food_queryString = naverService.queryString(food[f]);

		List<Object> naverRainList = naverService.getNaver(rain_queryString);
		List<Object> naverDustList = naverService.getNaver(dust_queryString);
		List<Object> naverFoodList = naverService.getNaver(food_queryString);

		for (int k = 0; k < 100; k++) {
			if (weather.get(k).getCategory().equals("PTY")) {
				if (weather.get(k).getFcstValue().equals("1")) {
					String js_queryString = naverService.js_queryString(rain_food[i]);
					List<Object> js_List = naverService.getNaver(js_queryString);

					model.addAttribute("LIST", naverRainList);
					model.addAttribute("JS_LIST", js_List);
					model.addAttribute("FOOD", rain_food[i]);
					break;
				}
			} else if (getDust.get(k).getStationName().equals("운암동")) {
				String str1 = getDust.get(k).getPm10Value();
				int intValue1 = Integer.parseInt(str1);

				if (getDust.get(i).getPm10Value().equals("-")) {
					break;
				}

				if (intValue1 > 80) {
					String js_queryString = naverService.js_queryString(dust_food[j]);
					List<Object> js_List = naverService.getNaver(js_queryString);

					model.addAttribute("LIST", naverDustList);
					model.addAttribute("JS_LIST", js_List);
					model.addAttribute("FOOD", dust_food[j]);
					break;
				}
			} else if (getDust.get(k).getStationName().equals("운암동")) {
				String str2 = getDust.get(k).getPm25Value();
				int intValue2 = Integer.parseInt(str2);

				if (getDust.get(i).getPm25Value().equals("-")) {
					break;
				}

				if (intValue2 > 35) {
					String js_queryString = naverService.js_queryString(dust_food[j]);
					List<Object> js_List = naverService.getNaver(js_queryString);

					model.addAttribute("LIST", naverDustList);
					model.addAttribute("JS_LIST", js_List);
					model.addAttribute("FOOD", dust_food[j]);
					break;
				}
			} else {
				String js_queryString = naverService.js_queryString(food[f]);
				List<Object> js_List = naverService.getNaver(js_queryString);

				model.addAttribute("LIST", naverFoodList);
				model.addAttribute("JS_LIST", js_List);
				model.addAttribute("FOOD", food[f]);
				break;
			}
		}

		model.addAttribute("WEATHER", weather);
		model.addAttribute("DUST", getDust);

		return "detail/list";
	}

}
