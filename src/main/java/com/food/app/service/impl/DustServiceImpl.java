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

		String str1 = list.get(0).getPm10Value();
		String str2 = list.get(0).getPm25Value();

		if (list.get(0).getPm10Value().equals("-")) {
			str1 = "0";
		} else if (list.get(0).getPm25Value().equals("-")) {
			str2 = "0";
		}

		int intValue1 = Integer.parseInt(str1);
		int intValue2 = Integer.parseInt(str2);

		if (list.get(0).getPm10Value().equals("-")) {
			list.get(0).setPm10Grade("측정 결과 없음");
		} else if (intValue1 > 80 && intValue1 <= 150) {
			list.get(0).setPm10Grade("나쁨");
		} else if (intValue1 > 150) {
			list.get(0).setPm10Grade("매우 나쁨");
		}

		if (list.get(0).getPm25Value().equals("-")) {
			list.get(0).setPm25Grade("측정 결과 없음");
		} else if (intValue2 > 35 && intValue2 <= 75) {
			list.get(0).setPm25Grade("나쁨");
		} else if (intValue2 > 75) {
			list.get(0).setPm25Grade("매우 나쁨");
		}

		int i = (int) (Math.random() * 5);

		String[] dust_food = { "콩나물국밥", "고등어", "굴", "쌀국수", "마라탕" };
		list.get(0).setDustFood(dust_food[i]);

		return list;
	}

}
