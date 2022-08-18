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
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.food.app.config.KeyConfig;
import com.food.app.model.WeatherVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class weatherTest {
    public static void main(String[] args) throws IOException, ParseException, JSONException {
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
		
		if(nowTime.getHour() == 02 || nowTime.getHour( )== 03 || nowTime.getHour() == 04) {
			time="0200";
		} else if(nowTime.getHour() == 05 || nowTime.getHour( )== 06 || nowTime.getHour() == 07) {
			time="0500";
		} else if(nowTime.getHour() == 8 || nowTime.getHour( )== 9 || nowTime.getHour() == 10) {
			time="0800";
		} else if(nowTime.getHour() == 11 || nowTime.getHour( )== 12 || nowTime.getHour() == 13) {
			time="1100";
		} else if(nowTime.getHour() == 14 || nowTime.getHour( )== 15 || nowTime.getHour() == 16) {
			time="1400";
		} else if(nowTime.getHour() == 17 || nowTime.getHour( )== 18 || nowTime.getHour() == 19) {
			time="1700";
		} else if(nowTime.getHour() == 20 || nowTime.getHour( )== 21 || nowTime.getHour() == 22) {
			time="2000";
		} else if(nowTime.getHour() == 23 || nowTime.getHour( )== 00 || nowTime.getHour() == 01) {
			time="2300";
		}
		
		String[] weather_code= {"없음","비","비/눈","눈","소나기"};
		
		try {
			weather_url += ("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + KeyConfig.KEY_1);
			weather_url += ("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
			weather_url += ("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("290", "UTF-8")); /*한 페이지 결과 수*/
			
			if (nowTime.getHour( )== 00 || nowTime.getHour() == 01) {
				weather_url +=("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(y_date, "UTF-8")); // 00시 / 01시 일 때 기준 날짜를 하루 전으로 설정
			} else {
				weather_url +=("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(dateForm.format(date), "UTF-8")); // 기준 날짜
			}

			weather_url += ("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(time, "UTF-8")); // 기준 시간 (0200 부터 3시간 단위)
			weather_url += ("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); // x 좌표
			weather_url += ("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("74", "UTF-8")); // y 좌표
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
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
        
        JSONObject json = XML.toJSONObject(retString);
        String jsonStr = json.toString(4);
       
        JSONObject jObject = new JSONObject(jsonStr);
        
        JSONObject response = jObject.getJSONObject("response");
        
        JSONObject body = response.getJSONObject("body");
        
        JSONObject items = body.getJSONObject("items");
        
        JSONArray item = items.getJSONArray("item");
      
        Gson gson = new Gson();
        List<WeatherVO> list = gson.fromJson(item.toString(), new TypeToken<List<WeatherVO>>(){}.getType());
        
//      System.out.println(list);
        
        System.out.println(dateForm.format(date));
        System.out.println(time);
        System.out.println();
        
        String weather = null;
        String tmperature = null;
        String rain = null;
        
        for(int i=0;i<item.length();i++){
        	JSONObject obj = item.getJSONObject(i);
        	
        	String category = obj.getString("category");
        	int st_date = obj.getInt("fcstDate");
        	int st_time = obj.getInt("fcstTime");
        	Object fcstValue = obj.get("fcstValue");

            if(category.equals("TMP")){
                tmperature = "기온은 "+fcstValue+"℃ 입니다.";
            }

            if(category.equals("SKY")){
                weather = "현재 날씨는 ";
                if(fcstValue.equals(1)) {
                    weather += "맑은 상태";
                }else if(fcstValue.equals(2)) {
                    weather += "비가 오는 상태 ";
                }else if(fcstValue.equals(3)) {
                    weather += "구름이 많은 상태 ";
                }else if(fcstValue.equals(4)) {
                    weather += "흐린 상태 ";
                }
            }
            
            if(category.equals("PTY")){
                rain = "강수 형태 : ";
                if(fcstValue.equals(0)) {
                	rain += weather_code[0];
                }else if(fcstValue.equals(1)) {
                	rain += weather_code[1];
                }else if(fcstValue.equals(2)) {
                	rain += weather_code[2];
                }else if(fcstValue.equals(3)) {
                	rain += weather_code[3];
                }else if(fcstValue.equals(4)) {
                	rain += weather_code[4];
                }
            }
            
            if(category.equals("SKY")) {
            	System.out.println(st_date + "\n" + st_time + "\n" + weather +"\n"+ tmperature);
            }
            
            if (category.equals("PTY")) {
            	System.out.println(rain + "\n");
            	break;
            }
            
        }
        
    }

}