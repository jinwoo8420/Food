<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Insert title here</title>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<style>
table {
	text-align: center;
}

img {
	width: 50px;
}

span.pm10 {
	font-size: 5px;
}
</style>

</head>
<body>
	<c:set var="loop_flag" value="false" />
	<c:forEach items="${WEATHER}" var="weather_list">
		<c:if test="${not loop_flag }">
			<c:if test="${weather_list.category eq 'TMP'}">
			현재기온 ${weather_list.fcstValue}℃
			</c:if>
			<c:set var="loop_flag" value="true" />
		</c:if>
	</c:forEach>

	<c:set var="loop_flag" value="false" />
	<c:forEach items="${WEATHER}" var="weather_list">
		<c:if test="${not loop_flag }">
			<c:if test="${weather_list.category eq 'SKY'}">
			날씨 
				<c:if test="${weather_list.fcstValue eq '1'}">
					맑음
					<img src="${rootPath}/static/img/Sunny.png">
				</c:if>

				<c:if test="${weather_list.fcstValue eq '3'}">
					구름 많음
					<img src="${rootPath}/static/img/Cloud.png">
				</c:if>

				<c:if test="${weather_list.fcstValue eq '4'}">
					흐림
					<img src="${rootPath}/static/img/Cloudy.png">
				</c:if>
				<c:set var="loop_flag" value="true" />
			</c:if>
		</c:if>
	</c:forEach>

	<c:set var="loop_flag" value="false" />
	<c:forEach items="${WEATHER}" var="weather_list">
		<c:if test="${not loop_flag }">
			<c:if test="${weather_list.category eq 'PTY'}">
			강수형태 
				<c:if test="${weather_list.fcstValue eq '0'}">
					없음
				</c:if>

				<c:if test="${weather_list.fcstValue eq '1'}">
					비
					<img src="${rootPath}/static/img/Rain.png">
				</c:if>

				<c:if test="${weather_list.fcstValue eq '2'}">
					비/눈
					<img src="${rootPath}/static/img/Rain.png">
					<img src="${rootPath}/static/img/Snow.png">
				</c:if>

				<c:if test="${weather_list.fcstValue eq '3'}">
					눈
					<img src="${rootPath}/static/img/Snow.png">
				</c:if>

				<c:if test="${weather_list.fcstValue eq '4'}">
					소나기
					<img src="${rootPath}/static/img/Rain.png">
				</c:if>
				<c:set var="loop_flag" value="true" />
			</c:if>
		</c:if>
	</c:forEach>

	<br>

	<div>
		<c:forEach items="${DUST}" var="dust_list">
			<c:if test="${dust_list.stationName eq '운암동'}">
					${dust_list.dataTime} / 미세먼지(PM10) 농도 : ${dust_list.pm10Value} / 미세먼지(PM2.5) 농도 :  ${dust_list.pm25Value}
		</c:if>
		</c:forEach>

		<br> <span class="pm10">* pm10 기준 ~ 30 좋음 / 31 ~ 80 보통 / 81 ~ 150 나쁨 / 151 ~ 매우 나쁨</span> <br> <span class="pm10">* pm2.5 기준 ~ 15 좋음 / 16 ~ 35 보통 / 36 ~ 75 나쁨 / 76 ~ 매우 나쁨</span>
	</div>

	<div class="food_list">
		<h3>음식 : ${FOOD}</h3>

		<br>

		<c:forEach items="${NAVER}" var="list">
			<div>
				<p>
					이름 : <b>${list.title}</b>
				</p>

				<c:if test="${empty list.link}">
					<p>홈페이지 : 제공된 주소가 없습니다</p>
				</c:if>

				<c:if test="${not empty list.link}">
					<p>홈페이지 : ${list.link}</p>

				</c:if>

				<p>주소 : ${list.address}</p>
				<p>도로명 주소 : ${list.roadAddress}</p>
				<br>
			</div>

		</c:forEach>
	</div>

</body>
</html>