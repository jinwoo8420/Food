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
img.weather_img {
	width: 50px;
}

span.pm10 {
	font-size: 5px;
}

img.img-responsive {
	margin: 0 auto;
	width: 150px;
	display: block;
}

div.weather_api {
	display: flex;
	justify-content: space-evenly;
}

div.weather_list {
	background-color: #c1cfff96;
	box-shadow: 2px 2px 2px 2px #a0add763;
	border-radius: 5px;
	padding-left: 10px;
	padding-right: 10px;
	line-height: 132px;
	border-radius: 5px;
	padding-left: 10px;
	padding-right: 10px;
	padding-left: 10px;
	padding-right: 10px;
	padding-right: 10px;
}

div.dust_list {
	background-color: #c1cfff96;
	box-shadow: 2px 2px 2px 2px #a0add763;
	padding: 10px;
	border-radius: 5px;
	text-align: center;
}

h3 {
	display: inline-block;
}

.food_list {
	padding-top: 20px;
	text-align: center;
	width: 50%;
	height: 50%;
	margin: 0 auto;
	background-color: #fca8f178;
	border-radius: 10px;
	box-shadow: 1px 0.5px 1px 0.5px #ca75ad8f;
	position: relative;
}

a#kakaotalk-sharing-btn {
	position: absolute;
	top: 0;
	right: 0;
	margin: 15px;
	cursor: pointer;
}
</style>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

</head>
<body>
	<img src="${rootPath}/static/img/main.png" class="img-responsive">
	<hr>

	<div class="weather_api">
		<div class="weather_list">
			<c:set var="loop_flag" value="false" />
			<c:forEach items="${WEATHER}" var="weather_list">
				<c:if test="${not loop_flag }">
					<c:if test="${weather_list.category eq 'TMP'}">
         현재기온 ${weather_list.fcstValue}℃ &nbsp&nbsp
         </c:if>
					<c:set var="loop_flag" value="true" />
				</c:if>
			</c:forEach>

			<c:set var="loop_flag" value="false" />
			<c:forEach items="${WEATHER}" var="weather_list">
				<c:if test="${not loop_flag }">
					<c:if test="${weather_list.category eq 'SKY'}">
						<c:if test="${weather_list.fcstValue eq '1'}">
               맑음
               <img class="weather_img" src="${rootPath}/static/img/Sunny.png"> &nbsp&nbsp
                  </c:if>

						<c:if test="${weather_list.fcstValue eq '3'}">
               구름 많음
               <img class="weather_img" src="${rootPath}/static/img/Cloud.png"> &nbsp&nbsp
                  </c:if>

						<c:if test="${weather_list.fcstValue eq '4'}">
               흐림
               <img class="weather_img" src="${rootPath}/static/img/Cloudy.png"> &nbsp&nbsp
                  </c:if>
						<c:set var="loop_flag" value="true" />
					</c:if>
				</c:if>
			</c:forEach>

			<c:set var="loop_flag" value="false" />
			<c:forEach items="${WEATHER}" var="weather_list">
				<c:if test="${not loop_flag }">
					<c:if test="${weather_list.category eq 'PTY'}">
         강수상태 
            <c:if test="${weather_list.fcstValue eq '0'}">
               없음
            </c:if>

						<c:if test="${weather_list.fcstValue eq '1'}">
               비
               <img class="weather_img" src="${rootPath}/static/img/Rain.png">
						</c:if>

						<c:if test="${weather_list.fcstValue eq '2'}">
               비/눈
               <img class="weather_img" src="${rootPath}/static/img/Rain.png">
							<img class="weather_img" src="${rootPath}/static/img/Snow.png">
						</c:if>

						<c:if test="${weather_list.fcstValue eq '3'}">
               눈
               <img class="weather_img" src="${rootPath}/static/img/Snow.png">
						</c:if>

						<c:if test="${weather_list.fcstValue eq '4'}">
               소나기
               <img class="weather_img" src="${rootPath}/static/img/Rain.png">
						</c:if>
						<c:set var="loop_flag" value="true" />
					</c:if>
				</c:if>
			</c:forEach>
		</div>

		<div class="dust_list">
			<c:forEach items="${DUST}" var="dust_list">
				<c:if test="${dust_list.stationName eq '운암동'}">
					<span style="font-size: 18px;">${dust_list.dataTime}</span>
					<br>
               미세먼지(PM10) 농도 : ${dust_list.pm10Value} &nbsp / &nbsp 미세먼지(PM2.5) 농도 :  ${dust_list.pm25Value}
         </c:if>
			</c:forEach>

			<br> <br> <span class="pm10">* pm10 기준 ~ 30 좋음 &nbsp /&nbsp 31 ~ 80 보통 &nbsp / &nbsp 81 ~ 150 나쁨 &nbsp / &nbsp 151 ~ 매우 나쁨</span> <br> <span class="pm10">* pm2.5 기준 ~ 15 좋음 &nbsp / &nbsp 16 ~ 35 보통 &nbsp / &nbsp 36 ~ 75 나쁨 &nbsp / &nbsp 76 ~ 매우 나쁨</span>
		</div>
	</div>

	<br>
	<br>

	<%-- 	<div class="food_list">
		<h3>오늘의 추천 메뉴는?</h3>
		<h4>* ${RAINFOOD} *</h4>
		<button class="kakao_btn">버튼</button>

		<br>

		<c:forEach items="${RAINLIST}" var="rain">
			<div>

				<h5>
					<b>${rain.title}</b>
				</h5>

				<c:if test="${empty rain.link}">
					<p>홈페이지 : 제공된 주소가 없습니다</p>
				</c:if>

				<c:if test="${not empty rain.link}">
					<p>홈페이지 : ${rain.link}</p>
				</c:if>

				<p>주소 : ${rain.address}</p>
				<p>도로명 주소 : ${rain.roadAddress}</p>

				<br>
			</div>

		</c:forEach>
	</div> --%>

	<div class="food_list">
		<h3>오늘의 추천 메뉴는?</h3>
		<h4>* ${DUSTFOOD} *</h4>

		<a id="kakaotalk-sharing-btn">
			<img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" />
		</a>

		<br>

		<c:forEach items="${DUSTLIST}" var="dust">
			<div>

				<h5>
					<b>${dust.title}</b>
				</h5>

				<c:if test="${empty dust.link}">
					<p>홈페이지 : 제공된 주소가 없습니다</p>
				</c:if>

				<c:if test="${not empty dust.link}">
					<p>홈페이지 : ${dust.link}</p>
				</c:if>

				<p>주소 : ${dust.address}</p>
				<p>도로명 주소 : ${dust.roadAddress}</p>

				<br>
			</div>
		</c:forEach>
	</div>

	<script type="text/javascript">
		Kakao.init('be01097258cdbbe3042dc5464adc574e');

		Kakao.Share
				.createDefaultButton({
					container : '#kakaotalk-sharing-btn',
					objectType : 'list',
					headerTitle : 'WEEKLY MAGAZINE',
					headerLink : {
						mobileWebUrl : 'https://developers.kakao.com',
						webUrl : 'https://developers.kakao.com',
					},
					contents : [
							{
								title : '취미의 특징, 탁구',
								description : '스포츠',
								imageUrl : 'http://k.kakaocdn.net/dn/bDPMIb/btqgeoTRQvd/49BuF1gNo6UXkdbKecx600/kakaolink40_original.png',
								link : {
									mobileWebUrl : 'https://developers.kakao.com',
									webUrl : 'https://developers.kakao.com',
								},
							},
							{
								title : '크림으로 이해하는 커피이야기',
								description : '음식',
								imageUrl : 'http://k.kakaocdn.net/dn/QPeNt/btqgeSfSsCR/0QJIRuWTtkg4cYc57n8H80/kakaolink40_original.png',
								link : {
									mobileWebUrl : 'https://developers.kakao.com',
									webUrl : 'https://developers.kakao.com',
								},
							},
							{
								title : '감성이 가득한 분위기',
								description : '사진',
								imageUrl : 'http://k.kakaocdn.net/dn/c7MBX4/btqgeRgWhBy/ZMLnndJFAqyUAnqu4sQHS0/kakaolink40_original.png',
								link : {
									mobileWebUrl : 'https://search.naver.com/search.naver?" + query',
									webUrl : 'https://search.naver.com/search.naver?' + query,
								},
							}, ],
				/* 	buttons : [ {
						title : '웹으로 보기',
						link : {
							mobileWebUrl : 'https://developers.kakao.com',
							webUrl : 'https://developers.kakao.com',
						},
					}, {
						title : '앱으로 보기',
						link : {
							mobileWebUrl : 'https://developers.kakao.com',
							webUrl : 'https://developers.kakao.com',
						},
					}, ], */
				});
	</script>

</body>
</html>