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
.img-responsive {
	margin: 0 auto;
	width: 150px;
	display: block;
}

div.btn {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	z-index: 10;
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

div.list_group {
	display: flex;
	width: 30%;
	height: 50%;
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
}

span.close {
	border-radius: 20px;
	cursor: default;
}

span.close:hover {
	background-color: #dda8a8 !important;
}

.a {
	background-color: pink;
}

hr {
	border: 2px solid black;
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
		<header class="w3-container">
			<div id="close_list">
				<span class="close w3-button w3-display-topright" style="font-size: 20px; color: white;"> &times; </span>
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