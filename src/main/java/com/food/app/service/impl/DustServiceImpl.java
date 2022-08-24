package com.food.app.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.food.app.config.KeyConfig;
import com.food.app.model.DustVO;
import com.food.app.service.DustService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class DustServiceImpl implements DustService {

	@Override
	public List<DustVO> getDust(String name) throws IOException {
		String dust_url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"; // 호출 url

		dust_url += "?" + URLEncoder.encode("serviceKey", "UTF-8");
		dust_url += "=" + KeyConfig.KEY_1;
		dust_url += "&" + URLEncoder.encode("returnType", "UTF-8");
		dust_url += "=xml";
		dust_url += "&" + URLEncoder.encode("numOfRows", "UTF-8");
		dust_url += "=10";
		dust_url += "&" + URLEncoder.encode("pageNo", "UTF-8");
		dust_url += "=1";
		dust_url += "&" + URLEncoder.encode("sidoName", "UTF-8");
		dust_url += "=" + URLEncoder.encode(name, "UTF-8");
		dust_url += "&" + URLEncoder.encode("ver", "UTF-8");
		dust_url += "=1.0";

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

		return list;
	}

}
