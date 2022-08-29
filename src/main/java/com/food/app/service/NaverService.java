package com.food.app.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface NaverService {
	public String queryString(String area, String search) throws UnsupportedEncodingException;

	public List<Object> getNaver(String queryString);
}
