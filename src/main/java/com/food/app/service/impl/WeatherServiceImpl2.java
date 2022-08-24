package com.food.app.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
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

import javax.net.ssl.HttpsURLConnection;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.food.app.config.APIConfig;
import com.food.app.config.KeyConfig;
import com.food.app.model.ApiParent;
import com.food.app.model.WeatherVO;
import com.food.app.service.WeatherService2;

public class WeatherServiceImpl2 implements WeatherService2 {

	@Override
	public List<Object> getWeather(String queryString) throws IOException, ParseException {
		URI restURI = null;

		try {
			restURI = new URI(queryString);
		} catch (URISyntaxException e) {
			return null;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json");

		HttpEntity<String> entity = new HttpEntity<String>("parameter", headers);

		RestTemplate restTemp = new RestTemplate();

		ResponseEntity<ApiParent<WeatherVO>> resData = null;

		resData = restTemp.exchange(restURI, HttpMethod.GET, entity,
				new ParameterizedTypeReference<ApiParent<WeatherVO>>() {
				});

		return resData.getBody().item;
	}

	@Override
	public String queryString(String mapX, String mapY) throws ParseException, IOException {
		String weather_url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"; // 호출 url
		String encodeSearch = null;

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

		encodeSearch += ("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + KeyConfig.KEY_1);
		encodeSearch += ("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		encodeSearch += ("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/

		if (nowTime.getHour() == 00 || nowTime.getHour() == 01) {
			encodeSearch += ("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(y_date, "UTF-8"));
		} else {
			encodeSearch += ("&" + URLEncoder.encode("base_date", "UTF-8") + "="
					+ URLEncoder.encode(dateForm.format(date), "UTF-8")); // 기준 날짜
		}

		encodeSearch += ("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8"));
		encodeSearch += ("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(mapX, "UTF-8")); // x 좌표
		encodeSearch += ("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(mapY, "UTF-8")); // y 좌표

		weather_url += encodeSearch;

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
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		return weather_url;
	}

	public String getJson(String queryString) {
		URL url = null;

		HttpsURLConnection httpCon = null;

		try {
			url = new URL(queryString);
			httpCon = (HttpsURLConnection) url.openConnection();

			httpCon.setRequestMethod("GET");

			httpCon.setRequestProperty(APIConfig.HEADER.ID, APIConfig.NAVER_CLIENT_ID);
			httpCon.setRequestProperty(APIConfig.HEADER.SEC, APIConfig.NAVER_CLIENT_SEC);

			int resCode = httpCon.getResponseCode();

			InputStreamReader is = null;
			BufferedReader buffer = null;

			if (resCode == 200) {
				is = new InputStreamReader(httpCon.getInputStream());
			} else {
				is = new InputStreamReader(httpCon.getErrorStream());
			}

			buffer = new BufferedReader(is);

			String retString = "";

			while (true) {
				String line = buffer.readLine();

				if (line == null)
					break;

				retString += line;
			}

			return retString;

		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

}