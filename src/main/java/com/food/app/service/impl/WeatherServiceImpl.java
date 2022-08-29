package com.food.app.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.app.config.KeyConfig;
import com.food.app.model.FoodVO;
import com.food.app.model.WeatherVO;
import com.food.app.service.NaverService;
import com.food.app.service.WeatherService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private NaverService naverService;

	@Override
	public List<WeatherVO> getWeather(String mapX, String mapY) throws IOException, ParseException {
		String weather_url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"; // 호출 url

		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMdd");

		Date today = new Date(); // 오늘 날짜
		String st_today = dateForm.format(today); // 오늘 날짜 String형 변환
		Date setDate = dateForm.parse(st_today); // 기준 날짜 설정

		Calendar cal = new GregorianCalendar(Locale.KOREA); // 한국 날짜 기준으로 설정
		cal.setTime(setDate); // 기준 날짜 설정
		cal.add(Calendar.DATE, -1); // 하루 전으로 설정
		String y_date = dateForm.format(cal.getTime()); // 하루 전 날짜 String 타입 지정

		LocalTime nowTime = LocalTime.now();

		String time = null;

		if (nowTime.getHour() == 02 || nowTime.getHour() == 03 || nowTime.getHour() == 04) {
			time = "0200";
		} else if (nowTime.getHour() == 05 || nowTime.getHour() == 06 || nowTime.getHour() == 07) {
			time = "0500";
		} else if (nowTime.getHour() == 8 || nowTime.getHour() == 9 || nowTime.getHour() == 10) {
			time = "0800";
		} else if (nowTime.getHour() == 11 || nowTime.getHour() == 12 || nowTime.getHour() == 13) {
			time = "1100";
		} else if (nowTime.getHour() == 14 || nowTime.getHour() == 15 || nowTime.getHour() == 16) {
			time = "1400";
		} else if (nowTime.getHour() == 17 || nowTime.getHour() == 18 || nowTime.getHour() == 19) {
			time = "1700";
		} else if (nowTime.getHour() == 20 || nowTime.getHour() == 21 || nowTime.getHour() == 22) {
			time = "2000";
		} else if (nowTime.getHour() == 23 || nowTime.getHour() == 00 || nowTime.getHour() == 01) {
			time = "2300";
		}

		weather_url += ("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + KeyConfig.KEY_1);
		weather_url += ("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/

		if (nowTime.getHour() == 00 || nowTime.getHour() == 01) {
			weather_url += ("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(y_date, "UTF-8"));
		} else {
			weather_url += ("&" + URLEncoder.encode("base_date", "UTF-8") + "="
					+ URLEncoder.encode(dateForm.format(date), "UTF-8")); // 기준 날짜
		}

		weather_url += ("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8"));
		weather_url += ("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(mapX, "UTF-8")); // x 좌표
		weather_url += ("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(mapY, "UTF-8")); // y 좌표

		URL url = new URL(weather_url.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		String retString = "";
		String line;
		while ((line = rd.readLine()) != null) {
			retString += line;
		}
		rd.close();
		conn.disconnect();

		JSONObject json = XML.toJSONObject(retString);
		String jsonStr = json.toString(4);

		JSONObject jObject = new JSONObject(jsonStr);

		JSONObject response = jObject.getJSONObject("response");

		JSONObject body = response.getJSONObject("body");

		JSONObject items = body.getJSONObject("items");

		JSONArray item = items.getJSONArray("item");

		Gson gson = new Gson();
		List<WeatherVO> list = gson.fromJson(item.toString(), new TypeToken<List<WeatherVO>>() {
		}.getType());

		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getCategory().equals("SKY")) {
				if (list.get(index).getFcstValue().equals("1")) {
					list.get(index).setSkyValue("맑음");
					break;
				} else if (list.get(index).getFcstValue().equals("3")) {
					list.get(index).setSkyValue("구름 많음");
					break;
				} else if (list.get(index).getFcstValue().equals("4")) {
					list.get(index).setSkyValue("흐림");
					break;
				}
			}
		}

		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getCategory().equals("PTY")) {
				if (list.get(index).getFcstValue().equals("0")) {
					list.get(index).setPtyValue("없음");
					break;
				} else if (list.get(index).getFcstValue().equals("1")) {
					list.get(index).setPtyValue("비");
					break;
				} else if (list.get(index).getFcstValue().equals("2") || list.get(index).getFcstValue().equals("3")) {
					list.get(index).setPtyValue("눈");
					break;
				} else if (list.get(index).getFcstValue().equals("4")) {
					list.get(index).setPtyValue("소나기");
					break;
				}
			}
		}

		return list;
	}

	@Override
	public List<Object> rainList(String area) throws UnsupportedEncodingException {
		int i = (int) (Math.random() * 12);
		FoodVO foodVO = new FoodVO();

		String[] rain_food = { "부대찌개", "아구찜", "해물탕", "칼국수", "수제비", "짬뽕", "우동", "치킨", "국밥", "김치전", "두부김치", "파전" };
		
		foodVO.setRainFood(rain_food[i]);
		
		String rain_queryString = naverService.queryString(area, foodVO.getRainFood());
		List<Object> naverRainList = naverService.getNaver(rain_queryString);

		return naverRainList;
	}

}
