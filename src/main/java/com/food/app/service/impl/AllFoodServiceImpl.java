package com.food.app.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.app.model.FoodVO;
import com.food.app.service.AllFoodService;
import com.food.app.service.NaverService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AllFoodServiceImpl implements AllFoodService {

	@Autowired
	private NaverService naverService;

	@Override
	public List<Object> foodList(String area) throws UnsupportedEncodingException {
		int i = (int) (Math.random() * 17);
		FoodVO foodVO = new FoodVO();

		String[] food = { "부대찌개", "아구찜", "해물탕", "칼국수", "수제비", "짬뽕", "우동", "치킨", "국밥", "김치전", "두부김치", "파전", "콩나물국밥",
				"고등어", "굴", "쌀국수", "마라탕" };

		foodVO.setAllFood(food[i]);

		String food_queryString = naverService.queryString(area, food[i]);
		List<Object> naverFoodList = naverService.getNaver(food_queryString);
		log.debug("All FOOD {}", foodVO);

		return naverFoodList;
	}

}
