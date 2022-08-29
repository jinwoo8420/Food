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
import com.food.app.model.FoodVO;
import com.food.app.model.WeatherVO;
import com.food.app.service.AllFoodService;
import com.food.app.service.DustService;
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

	@RequestMapping(value = "/{mapx},{mapy},{dust}/list")
	public String list(@PathVariable("mapx") String mapx, @PathVariable("mapy") String mapy,
			@PathVariable("dust") String dust, Model model) throws IOException, ParseException {

		FoodVO foodVO = new FoodVO();

		List<WeatherVO> weather = weatherService.getWeather(mapx, mapy);
		List<Object> rainFood = weatherService.rainList(dust);

		List<DustVO> getDust = dustService.getDust(dust);
		List<Object> dustFood = dustService.dustList(dust);

		List<Object> allFood = foodService.foodList(dust);

		if (weather.get(6).getCategory().equals("PTY")) {
			if (weather.get(6).getFcstValue().equals("1")) {
				model.addAttribute("LIST", rainFood);
				model.addAttribute("FOOD", foodVO.getRainFood());
				return "detail/list";
			}
		}

		if (getDust.get(0) != null) {
			if (getDust.get(0).getPm10Grade().equals("나쁨") || getDust.get(0).getPm10Grade().equals("매우 나쁨")) {
				model.addAttribute("LIST", dustFood);
				model.addAttribute("FOOD", foodVO.getDustFood());
				return "detail/list";
			} else if (getDust.get(0).getPm25Grade().equals("나쁨") || getDust.get(0).getPm25Grade().equals("매우 나쁨")) {
				model.addAttribute("LIST", dustFood);
				model.addAttribute("FOOD", foodVO.getDustFood());
				return "detail/list";
			}
		}

		model.addAttribute("LIST", allFood);
		model.addAttribute("FOOD", foodVO.getAllFood());
		log.debug("FOOD {}", foodVO);

		model.addAttribute("WEATHER", weather);
		model.addAttribute("DUST", getDust);

		return "detail/list";
	}

}