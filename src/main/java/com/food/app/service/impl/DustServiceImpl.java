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

		try {
			dust_url += ("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + KeyConfig.KEY_1); /*Service Key*/
			dust_url += ("&" + URLEncoder.encode("returnType", "UTF-8") + "="
					+ URLEncoder.encode("xml", "UTF-8")); /*xml 또는 json*/
			dust_url += ("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
			dust_url += ("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
			dust_url += ("&" + URLEncoder.encode("sidoName", "UTF-8") + "=" + URLEncoder.encode(name,
					"UTF-8")); /*시도 이름(전국, 서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종)*/
			dust_url += ("&" + URLEncoder.encode("ver", "UTF-8") + "="
					+ URLEncoder.encode("1.0", "UTF-8")); /*버전별 상세 결과 참고*/
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

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
