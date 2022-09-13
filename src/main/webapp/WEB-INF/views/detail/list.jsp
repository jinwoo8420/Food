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
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=zh678sfagk&submodules=geocoder"></script>

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

div.food_list {
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

div.map_view {
	display: flex;
	width: 70%;
	height: 45%;
	position: absolute;
	align-items: center;
	justify-content: center;
	border-radius: 5%;
	background-color: #ccd2d2;
	border: 2px solid #eeaaaa;
	opacity: 0;
	transition: 0.5s;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	z-index: -10;
}

span.close {
	border-radius: 20px;
	cursor: default;
}

p#mapx, p#mapy {
	display: none;
}
</style>

</head>
<body>
	<img src="${rootPath}/static/img/main.png" class="img-responsive">
	<hr>

	<div class="weather_api">
		<div class="weather_list">

			현재기온 ${WEATHER[0].fcstValue}℃ &nbsp&nbsp ${WEATHER[5].skyValue} &nbsp&nbsp

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
			<span style="font-size: 18px;">${DUST[0].dataTime}</span> <br> 미세먼지(PM10) 농도 : ${DUST[0].pm10Value} &nbsp / &nbsp 미세먼지(PM2.5) 농도 : ${DUST[0].pm25Value} <br> <br> <span class="pm10">* pm10 기준 ~ 30 좋음 &nbsp /&nbsp 31 ~ 80 보통 &nbsp / &nbsp 81 ~ 150 나쁨 &nbsp / &nbsp 151 ~ 매우 나쁨</span> <br> <span class="pm10">* pm2.5 기준 ~ 15 좋음 &nbsp / &nbsp 16 ~ 35 보통 &nbsp / &nbsp 36 ~ 75 나쁨 &nbsp / &nbsp 76 ~ 매우 나쁨</span>
		</div>
	</div>

	<br>
	<br>

	<div class="food_list">
		<h1 class="menu">오늘의 추천 메뉴는?</h1>
		<h2 class="food_name">* ${FOOD} *</h2>

		<a id="kakaotalk-sharing-btn"> <img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" />
		</a> <a id="back-btn" href="${rootPath}/intro"> <img src="${rootPath}/static/img/navbar-back.svg" />
		</a> <br>

		<c:forEach items="${LIST}" var="food_list">
			<div>
				<h5>
					<b> <a id="food_title">${food_list.title}</a>
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

				<p id="mapx">${food_list.mapx}</p>
				<p id="mapy">${food_list.mapy}</p>

				<br>
			</div>

			<div class="map_view">
				<header class="w3-container">
					<div id="close_list">
						<span class="close w3-button w3-display-topright" style="font-size: 20px; color: white;"> &times; </span>
					</div>
				</header>

				<div id="map" style="width: 100%; height: 400px; margin-right: 30px;"></div>

				<button class="food_title w3-display-bottommiddle" onclick="window.open('https://search.naver.com/search.naver?query=${DUST[0].sidoName} <c:out value='${food_list.title.replaceAll("\\\<.*?\\\>","")}' />')">상세정보</button>
			</div>
		</c:forEach>
	</div>

	<script>
		const open = document.querySelector("a#food_title");
		const openAll = document.querySelectorAll("a#food_title");
		const close = document.querySelectorAll("span.close");
		const map_view = document.querySelectorAll("div.map_view");
		
		const map_id = document.querySelectorAll("div#map");
		const mapx = document.querySelectorAll("p#mapx");
		const mapy = document.querySelectorAll("p#mapy");
		
		close.disabled = true;
		
		for (let i = 0; i < openAll.length; i++) {
			openAll[i].addEventListener("click", () => {
				map_view[i].style.opacity = 1;
				map_view[i].style.zIndex = "10";
				close[i].disabled = false;
				
				var tm128 = new naver.maps.Point(mapx[i].innerText, mapy[i].innerText);
				var utmk = naver.maps.TransCoord.fromTM128ToUTMK(tm128);
				var latlng = naver.maps.TransCoord.fromUTMKToLatLng(utmk);
				
				var map = new naver.maps.Map(map_id[i], {
				    center: latlng,
				    zoom: 15
				});

				var marker = new naver.maps.Marker({
				    position: latlng,
				    map: map
				});
			});
		}
		
		for (let i = 0; i < close.length; i++) {
			close[i].addEventListener("click", () => {
				map_view[i].style.opacity = 0;
				map_view[i].style.zIndex = "-10";
			    close[i].disabled = true;
			});
		}
	</script>

	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script type="text/javascript">
		Kakao.init("be01097258cdbbe3042dc5464adc574e");

		Kakao.Share
				.createDefaultButton({
					container : "#kakaotalk-sharing-btn",
					objectType : "list",
					headerTitle : '${DUST[0].sidoName} ${FOOD} 맛집',
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