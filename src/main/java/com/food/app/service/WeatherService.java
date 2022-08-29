package com.food.app.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import com.food.app.model.WeatherVO;

public interface WeatherService {
	public List<WeatherVO> getWeather(String mapX, String mapY) throws IOException, ParseException;

	public List<Object> rainList(String area) throws UnsupportedEncodingException;
}
