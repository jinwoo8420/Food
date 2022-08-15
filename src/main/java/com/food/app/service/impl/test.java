package com.food.app.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.food.app.config.KeyConfig;

public class test {
    public static void main(String[] args) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*URL*/
        
        Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMdd");
		
		Date today = new Date(); // 오늘 날짜
		String st_today = dateForm.format(today); // 오늘 날짜 String형 변환
		Date setDate = dateForm.parse(st_today); // 기준 날짜 설정

		Calendar cal = new GregorianCalendar(Locale.KOREA); // 한국 날짜 기준으로 설정
		cal.setTime(setDate); // 기준 날짜 설정
		cal.add(Calendar.DATE, -1); // 하루 전으로 설정
		String y_date = dateForm.format(cal.getTime()); // 하루 전 날짜 String 타입 지정
		
		LocalTime now = LocalTime.now();
		
		String test = null;
		
//		System.out.println(now.getHour());
		
		if(now.getHour() == 02 || now.getHour( )== 03 || now.getHour() == 04) {
			test="0200";
		} else if(now.getHour() == 05 || now.getHour( )== 06 || now.getHour() == 07) {
			test="0500";
		} else if(now.getHour() == 8 || now.getHour( )== 9 || now.getHour() == 10) {
			test="0800";
		} else if(now.getHour() == 11 || now.getHour( )== 12 || now.getHour() == 13) {
			test="1100";
		} else if(now.getHour() == 14 || now.getHour( )== 15 || now.getHour() == 16) {
			test="1400";
		} else if(now.getHour() == 17 || now.getHour( )== 18 || now.getHour() == 19) {
			test="1700";
		} else if(now.getHour() == 20 || now.getHour( )== 21 || now.getHour() == 22) {
			test="2000";
		} else if(now.getHour() == 23) {
			test="2300";
		} else if(now.getHour( )== 00 || now.getHour() == 01) {
			test="2300";	
		}
		
		String[] weather_code= {"없음","비","비/눈","소나기","빗방울","빗방울/눈날림","눈날림"};
        
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + KeyConfig.KEY_1);
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        
        if (now.getHour( )== 00 || now.getHour() == 01) {
        	urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(y_date, "UTF-8")); // 기준 날짜
        } else {
        	urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(dateForm.format(date), "UTF-8")); // 기준 날짜
        }
        
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(test, "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("74", "UTF-8")); /*예보지점의 Y 좌표값*/
        
        URL url = new URL(urlBuilder.toString());
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
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
//        System.out.println(sb.toString());
    }
}
