package com.food.app.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface WeatherService2 {
	public String queryString(String mapX, String mapY) throws ParseException, IOException;
	
	public List<Object> getWeather(String queryString) throws IOException, ParseException;
}
