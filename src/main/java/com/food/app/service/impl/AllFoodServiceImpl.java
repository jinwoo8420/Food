package com.food.app.service.impl;

import org.springframework.stereotype.Service;

import com.food.app.service.AllFoodService;

@Service
public class AllFoodServiceImpl implements AllFoodService {

	@Override
	public String getFood() {
		int i = (int) (Math.random() * 17);

		String[] food = { "부대찌개", "아구찜", "해물탕", "칼국수", "수제비", "짬뽕", "우동", "치킨", "국밥", "김치전", "두부김치", "파전", "콩나물국밥",
				"고등어", "굴", "쌀국수", "마라탕" };
		
		return food[i];
	}

}
