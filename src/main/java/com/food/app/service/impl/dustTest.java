package com.food.app.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.food.app.config.KeyConfig;
import com.food.app.model.DustVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class dustTest {
	public static void main(String[] args) throws IOException {
		String dust_url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"; // 호출 url
		String encodeParams = null;

		try {
			encodeParams = "?" + URLEncoder.encode("serviceKey", "UTF-8");
			encodeParams += "=" + KeyConfig.KEY_1;
			encodeParams += "&" + URLEncoder.encode("returnType", "UTF-8");
			encodeParams += "=xml";
			encodeParams += "&" + URLEncoder.encode("numOfRows", "UTF-8");
			encodeParams += "=10";
			encodeParams += "&" + URLEncoder.encode("pageNo", "UTF-8");
			encodeParams += "=1";
			encodeParams += "&" + URLEncoder.encode("sidoName", "UTF-8");
			encodeParams += "="+URLEncoder.encode("광주", "UTF-8");
			encodeParams += "&" + URLEncoder.encode("ver", "UTF-8");
			encodeParams += "=1.0";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		dust_url += encodeParams;

		URL url = new URL(dust_url.toString());
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
		
//		System.out.println(dust_url);
//		System.out.println(retString.toString());

		JSONObject json = XML.toJSONObject(retString);
		String jsonStr = json.toString(4);

		JSONObject jObject = new JSONObject(jsonStr);

		JSONObject response = jObject.getJSONObject("response");

		JSONObject body = response.getJSONObject("body");

		JSONObject items = body.getJSONObject("items");

		JSONArray item = items.getJSONArray("item");

		Gson gson = new Gson();
		List<DustVO> list = gson.fromJson(item.toString(), new TypeToken<List<DustVO>>() {
		}.getType());

		System.out.println(list);
	}
}