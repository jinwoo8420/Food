package com.food.app.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.food.app.model.DustVO;

public interface DustService {
	public List<DustVO> getDust(String name) throws IOException;
	
	public List<Object> dustList(String area) throws UnsupportedEncodingException;
}
