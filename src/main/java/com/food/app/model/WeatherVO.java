package com.food.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class WeatherVO {
	private String fcstDate;
	private String fcstTime;
	private String category;
	private String fcstValue;
	private String baseDate;
	private String baseTime;
	private String nx;
	private String ny;
	private String skyValue;
	private String ptyValue;
	private String rainFood;
}
