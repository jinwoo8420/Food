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
public class DustVO {
	private String sidoName;
	private String dataTime;
	private String pm10Value;
	private String pm25Value;
	private String stationName;
}
