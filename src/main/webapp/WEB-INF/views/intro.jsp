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

<link href="https://fonts.googleapis.com/css2?family=Hi+Melody&display=swap" rel="stylesheet">

<style>
img.img-responsive {
	margin: 0 auto;
	width: 150px;
	display: block;
}

div.btn {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

button.btn {
	font-family: 'Hi Melody', cursive;
	padding: 10px 15px;
	margin-right: 10px;
	background-color: #ffffff;
	border: none;
	border-radius: 5px;
	font-size: 20px;
}

button.btn:hover {
	background-color: #ffaad47d;
	border: 1px solid #ffffff;
}

body {
	background-color: #e7dcdc;
}

span.close {
	border-radius: 20px;
	cursor: default;
}

span.close:hover {
	background-color: #dda8a8 !important;
}

hr {
	border: 2px solid black;
}

div.area {
	text-align: center;
}
</style>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<script>
	const rootPath = "${rootPath}"
</script>

<script src="${rootPath}/static/js/intro.js?ver=2"></script>

</head>
<body>
	<img src="${rootPath}/static/img/main.png" class="img-responsive">

	<div class="btn">
		<div class="area">
			<button class="open btn seoul" data-mapx="60" data-mapy="127" data-dust="서울" type="button">서울</button>
			<button class="open btn incheon" data-mapx="54" data-mapy="125" data-dust="인천" type="button">인천</button>
			<button class="open btn sejong" data-mapx="65" data-mapy="104" data-dust="세종" type="button">세종</button>
			<button class="open btn daejeon" data-mapx="68" data-mapy="100" data-dust="대전" type="button">대전</button>
			<button class="open btn daegu" data-mapx="89" data-mapy="90" data-dust="대구" type="button">대구</button>
			<button class="open btn gwangju" data-mapx="59" data-mapy="74" data-dust="광주" type="button">광주</button>
			<button class="open btn ulsan" data-mapx="102" data-mapy="84" data-dust="울산" type="button">울산</button>
			<button class="open btn busan" data-mapx="97" data-mapy="74" data-dust="부산" type="button">부산</button>
			<button class="open btn jeju" data-mapx="53" data-mapy="38" data-dust="제주" type="button">제주</button>
		</div>

		<br>

		<div class="area">
			<button class="open btn gangwon" data-mapx="73" data-mapy="134" data-dust="강원" type="button">강원</button>
			<button class="open btn gyeonggi" data-mapx="60" data-mapy="122" data-dust="경기" type="button">경기</button>
			<button class="open btn chungnam" data-mapx="63" data-mapy="110" data-dust="충남" type="button">충남</button>
			<button class="open btn chungbuk" data-mapx="69" data-mapy="107" data-dust="충북" type="button">충북</button>
			<button class="open btn gyeongnam" data-mapx="91" data-mapy="77" data-dust="경남" type="button">경남</button>
			<button class="open btn gyeongbuk" data-mapx="102" data-mapy="94" data-dust="경북" type="button">경북</button>
			<button class="open btn jeonnam" data-mapx="50" data-mapy="66" data-dust="전남" type="button">전남</button>
			<button class="open btn jeonbuk" data-mapx="69" data-mapy="89" data-dust="전북" type="button">전북</button>
		</div>
	</div>

</body>
</html>