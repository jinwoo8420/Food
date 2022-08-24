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
body {
	font-weight: 400;
	font-size: 13px;
	margin: 0 auto;
	color: #000;
	padding-top: 43px !important;
	padding-bottom: 45px !important;
	background-color: #fff;
	overflow-x: hidden;
	max-width: 640px;
	height: 800px;
}

.img-responsive {
	margin: 0 auto;
	width: 150px;
	position: absolute;
	top: 10%;
	left: 50%;
	transform: translate(-50%, -50%);
}

div.btn {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	z-index: 10;
}

hr {
	border-bottom: 2px solid black;
}

button.btn {
	font-family: 'Hi Melody', cursive;
	padding: 10px 15px;
	margin-right: 10px;
	background-color: #95d9f9;
	border: none;
	border-radius: 5px;
	font-size: 20px;
}

button.btn:hover {
	background-color: white;
	border: 1px solid #95d9f9;
}

div.list_group {
	display: flex;
	width: 30%;
	height: 50%;
	position: absolute;
	align-items: center;
	justify-content: center;
	border-radius: 5%;
	background-color: #178ec7;
	border: 1px solid #aaa;
	opacity: 0;
	transition: 0.5s;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

span.close {
	border-radius: 20px;
	cursor: default;
}

span.close:hover {
	background-color: #dda8a8 !important;
}
</style>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<script>
	const rootPath = "${rootPath}"
</script>

<script src="${rootPath}/static/js/intro.js?ver=1"></script>

</head>
<body>
	<img src="${rootPath}/static/img/main.png" class="img-responsive">

	<div class="btn">
		<button class="open btn">광주</button>
		<button class="open btn">서울</button>
		<button class="open btn">제주</button>
		<button class="open btn">부산</button>
		<button class="open btn">대구</button>
	</div>

	<div class="list_group">
		<header class="w3-container w3-teal">
			<div id="close_list">
				<span class="close w3-button w3-display-topright" style="font-size: 20px"> &times; </span>
			</div>
		</header>

		<button class="list_group_item btn" data-mapx="59" data-mapy="74" data-dust="광주" type="button">화정동</button>
		<button class="list_group_item btn" data-mapx="59" data-mapy="74" data-dust="광주" type="button">풍암동</button>
		<button class="list_group_item btn" data-mapx="60" data-mapy="74" data-dust="광주" type="button">동명동</button>
		<button class="list_group_item btn" data-mapx="59" data-mapy="73" data-dust="광주" type="button">봉선동</button>
		<button class="list_group_item btn" data-mapx="60" data-mapy="75" data-dust="광주" type="button">각화동</button>
	</div>

	<script>
   const open = document.querySelector("button.open");
   const openAll = document.querySelectorAll(".open");
   const close = document.querySelector("span.close");
   const list = document.querySelector("div.list_group");
   
   close.disabled = true;
   
   open.addEventListener("click", () => {
      list.style.opacity = 1;
      list.style.zIndex = "20";
      close.disabled = false;
       
       for ( var i = 0; i < openAll.length; i++ ) {
          openAll[i].style.opacity = 0;
       }
     });

   close.addEventListener("click", () => {
       list.style.opacity = 0;
       list.style.zIndex = "0";
       close.disabled = true;
       
       for ( var i = 0; i < openAll.length; i++ ) {
           openAll[i].style.opacity = 1;
       }
     });
   </script>

</body>
</html>