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
<link href="https://fonts.googleapis.com/css2?family=Hi+Melody&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<style>
* {
	font-family: 'Jua', sans-serif;
}

a {
	text-decoration: none;
}

img.weather_img {
	width: 50px;
}

span.pm10 {
	font-size: 5px;
}

#kakaotalk-sharing-btn:hover {
	box-shadow: 2px 2px 2px black;
	border-radius: 13px;
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
	background-color: #ffffffbf;
	box-shadow: 2px 2px 2px 2px #a0add763;
	border-radius: 5px;
	padding-left: 10px;
	padding-right: 10px;
	line-height: 132px;
}

div.dust_list {
	background-color: #ffffffbf;
	box-shadow: 2px 2px 2px 2px #a0add763;
	padding: 10px;
	border-radius: 5px;
	text-align: center;
}

h1.menu {
	font-family: 'Hi Melody', cursive;
	display: inline-block;
	font-weight: bold;
}

h2.food_name {
	font-family: 'Hi Melody', cursive;
}

body {
	background-color: #e7dcdc;
}

.food_list {
	padding-top: 20px;
	text-align: center;
	width: 50%;
	height: 50%;
	margin: 0 auto;
	margin-bottom: 20px;
	background-color: #dfe4e4;
	border: 2px solid #eeaaaa;
	border-radius: 30px;
	box-shadow: 1px 0.5px 1px 0.5px #ca75ad8f;
	position: relative;
	border: 2px solid #eeaaaa;
}

a#kakaotalk-sharing-btn {
	position: absolute;
	top: 0;
	right: 0;
	margin: 15px;
	cursor: pointer;
}

a#back-btn {
	position: absolute;
	top: 0;
	left: 0;
	margin: 15px;
	cursor: pointer;
}

a#food_title:hover {
	border-bottom: 2px solid black;
}
</style>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>
<body>
	<img src="${rootPath}/static/img/main.png" class="img-responsive">
	<hr>

	<div class="weather_api">
		<div class="weather_list">

			현재기온 ${WEATHER[0].fcstValue}℃ &nbsp&nbsp

			${WEATHER[5].skyValue} &nbsp&nbsp

			<c:if test="${WEATHER[5].fcstValue eq '1'}">
				<img class="weather_img" src="${rootPath}/static/img/Sunny.png"> &nbsp&nbsp
			</c:if>

			<c:if test="${WEATHER[5].fcstValue eq '3'}">
				<img class="weather_img" src="${rootPath}/static/img/Cloud.png"> &nbsp&nbsp
	        </c:if>

			<c:if test="${WEATHER[5].fcstValue eq '4'}">
				<img class="weather_img" src="${rootPath}/static/img/Cloudy.png"> &nbsp&nbsp
	        </c:if>

			강수상태 ${WEATHER[6].ptyValue} &nbsp&nbsp
			
			<c:if test="${WEATHER[6].fcstValue eq '1' || WEATHER[6].fcstValue eq '4'}">
				<img class="weather_img" src="${rootPath}/static/img/Rain.png">
			</c:if>

			<c:if test="${WEATHER[6].fcstValue eq '2' || WEATHER[6].fcstValue eq '3'}">
				<img class="weather_img" src="${rootPath}/static/img/Snow.png">
			</c:if>
		</div>

		<div class="dust_list">
			<span style="font-size: 18px;">${DUST[0].dataTime}</span>
			
			<br>
			
			미세먼지(PM10) 농도 : ${DUST[0].pm10Value} &nbsp / &nbsp 미세먼지(PM2.5) 농도 : ${DUST[0].pm25Value} 
			
			<br> <br> 
			
			<span class="pm10">* pm10 기준 ~ 30 좋음 &nbsp /&nbsp 31 ~ 80 보통 &nbsp / &nbsp 81 ~ 150 나쁨 &nbsp / &nbsp 151 ~ 매우 나쁨</span> 
			
			<br>
			
			<span class="pm10">* pm2.5 기준 ~ 15 좋음 &nbsp / &nbsp 16 ~ 35 보통 &nbsp / &nbsp 36 ~ 75 나쁨 &nbsp / &nbsp 76 ~ 매우 나쁨</span>
		</div>
	</div>

	<br>
	<br>

	<div class="food_list">
		<h2 class="menu">오늘의 추천 메뉴는?</h2>
		<h3 class="food_name">* ${FOOD} *</h3>

		<a id="kakaotalk-sharing-btn"> <img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" />
		</a> <a id="back-btn" href="${rootPath}/intro"> <img src="${rootPath}/static/img/navbar-back.svg" />
		</a> <br>

		<c:forEach items="${LIST}" var="food_list">
			<div>
				<h5>
					<b> <a id="food_title" target='_blank' href="https://search.naver.com/search.naver?query=광주 <c:out value='${food_list.title.replaceAll("\\\<.*?\\\>","")}' />">${food_list.title}</a>
					</b>
				</h5>

				<c:if test="${empty food_list.link}">
					<p>홈페이지 : 제공된 주소가 없습니다</p>
				</c:if>

				<c:if test="${not empty food_list.link}">
					<p>홈페이지 : ${food_list.link}</p>
				</c:if>

				<p>주소 : ${food_list.address}</p>
				<p>도로명 주소 : ${food_list.roadAddress}</p>

				<br>
			</div>
		</c:forEach>
	</div>

	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script type="text/javascript">
		Kakao.init("be01097258cdbbe3042dc5464adc574e");

		Kakao.Share
				.createDefaultButton({
					container : "#kakaotalk-sharing-btn",
					objectType : "list",
					headerTitle : 'FOOD',
					headerLink : {
						mobileWebUrl : '',
						webUrl : '',
					},
					contents : [
						<c:forEach items="${LIST}" var="food_list" begin="1" end="3">
							{
								title : '<c:out value='${food_list.title.replaceAll("\\\<.*?\\\>","")}' />', 
								description : '${food_list.address}',
								imageUrl:
							        'http://k.kakaocdn.net/dn/bDPMIb/btqgeoTRQvd/49BuF1gNo6UXkdbKecx600/kakaolink40_original.png',
								link : {
									mobileWebUrl: "https://search.naver.com/search.naver?query=" + '${DUST[0].sidoName} ' + '<c:out value='${food_list.title.replaceAll("\\\<.*?\\\>","")}' />',
							        webUrl: "https://search.naver.com/search.naver?query=" + '${DUST[0].sidoName} ' + '<c:out value='${food_list.title.replaceAll("\\\<.*?\\\>","")}' />',
								},
							},
						</c:forEach>
					],
					buttons: [
					    {
					        title: '날씨 보기',
					        link: {
					          mobileWebUrl: 'https://search.naver.com/search.naver?query=' + '${DUST[0].sidoName}' + '날씨',
					          webUrl: 'https://search.naver.com/search.naver?query=' + '${DUST[0].sidoName}' + '날씨',
					        },
					      },
					    ],
					  });
		
	</script>
</body>
</html>