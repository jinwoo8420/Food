package com.food.app.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AllFoodService {
	public List<Object> foodList(String area) throws UnsupportedEncodingException;
}
