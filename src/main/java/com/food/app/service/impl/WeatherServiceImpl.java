package com.food.app.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.food.app.config.KeyConfig;
import com.food.app.model.WeatherVO;
import com.food.app.service.WeatherService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Override
	public List<WeatherVO> getWeather(String mapX, String mapY) throws IOException {
		String weather_url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?"; // 호출 url
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMdd");
		
		try {
			weather_url += ("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + KeyConfig.KEY_1);
			weather_url += ("?" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
			weather_url += ("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(dateForm.format(date), "UTF-8")); // 기준 날짜
			weather_url += ("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0800", "UTF-8")); // 기준 시간 (0200 부터 3시간 단위)
			weather_url += ("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(mapX, "UTF-8")); // x 좌표
			weather_url += ("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(mapY, "UTF-8")); // y 좌표
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//파일 읽어들이기
		 URL url = new URL(weather_url.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        String retString = "";
	        String line;
	        while ((line = rd.readLine()) != null) {
	        	retString+=line;
			}
			rd.close();
	        conn.disconnect();
        
        //xml json으로 형변환
        JSONObject json = XML.toJSONObject(retString);
        String jsonStr = json.toString(4);
       
        // JSONObject형식으로 지정
        JSONObject jObject = new JSONObject(jsonStr);
        
        //response 안으로
        JSONObject response = jObject.getJSONObject("response");
        
        // body 안으로
        JSONObject body = response.getJSONObject("body");
        
        //items 안으로
        JSONObject items = body.getJSONObject("items");
        
        //item은 배열로 생성
        JSONArray item = items.getJSONArray("item");
      
        //json 배열을 List형으로 변형해서 VO에 저장
        Gson gson = new Gson();
        List<WeatherVO> list = gson.fromJson(item.toString(), new TypeToken<List<WeatherVO>>(){}.getType());
        
        //값 지정해서 출력 성공!
        
        return list;
	}

}
