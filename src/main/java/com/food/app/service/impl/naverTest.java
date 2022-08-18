package com.food.app.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Collections;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.food.app.config.APIConfig;
import com.food.app.model.NaverParent;
import com.food.app.model.NaverVO;

public class naverTest {
	public static void main(String[] args) throws UnsupportedEncodingException, URISyntaxException {
		String queryString = APIConfig.NAVER_API;
		String encodeSearch = null;

		int i = (int) (Math.random() * 12);
		int j = (int) (Math.random() * 5);

		String[] rain_food = { "부대찌개", "아구찜", "해물탕", "칼국수", "수제비", "짬뽕", "우동", "치킨", "국밥", "김치전", "두부김치", "파전" };
		String[] dust_food = { "콩나물국밥", "고등어", "굴", "쌀국수", "마라탕" };

		encodeSearch = "?" + URLEncoder.encode("sort", "UTF-8");
		encodeSearch += "=comment";
		encodeSearch += "&" + URLEncoder.encode("query", "UTF-8");
//		encodeSearch += "=" + URLEncoder.encode("광주 " + rain_food[i] + " 맛집", "UTF-8");
		encodeSearch += "=" + URLEncoder.encode("광주 " + dust_food[j] + " 맛집", "UTF-8");
		encodeSearch += "&" + URLEncoder.encode("display", "UTF-8");
		encodeSearch += "=10";

		queryString += encodeSearch;

		URI restURI = null;

		restURI = new URI(queryString);

		HttpHeaders headers = new HttpHeaders();
		headers.set(APIConfig.HEADER.ID, APIConfig.NAVER_CLIENT_ID);
		headers.set(APIConfig.HEADER.SEC, APIConfig.NAVER_CLIENT_SEC);

		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>("parameter", headers);

		RestTemplate restTemp = new RestTemplate();

		ResponseEntity<NaverParent<NaverVO>> resData = null;

		resData = restTemp.exchange(restURI, HttpMethod.GET, entity,
				new ParameterizedTypeReference<NaverParent<NaverVO>>() {
				});

//		System.out.println("음식 : " + rain_food[i] + "\n");
		System.out.println("음식 : " + dust_food[j] + "\n");
		System.out.println(queryString + "\n");
		System.out.println(resData.getBody().items);

	}
}
