package com.food.app.config;

public class APIConfig {
	public static final String NAVER_CLIENT_ID = "1OMHVT8je8xCrMM01XgM";
	public static final String NAVER_CLIENT_SEC = "pAVSzOy6WA";

	public static class HEADER {
		public static final String ID = "X-Naver-Client-Id";
		public static final String SEC = "X-Naver-Client-Secret";
	}
	
	public static final String NAVER_API = "https://openapi.naver.com/v1/search/local.json";
	
	public static final String APP_JSON = "application/json;charset=UTF-8";
}
