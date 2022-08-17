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
<style>
/* html::-webkit-scrollbar, ul::-webkit-scrollbar, div::-webkit-scrollbar {
	width: 0.0em;
	height: 0.0em;
}

html::-webkit-scrollbar-track, ul::-webkit-scrollbar-track, div::-webkit-scrollbar-track,
	body::-webkit-scrollbar-track {
	-webkit-box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.2);
}

html::-webkit-scrollbar-thumb, ul::-webkit-scrollbar-thumb, div::-webkit-scrollbar-thumb,
	body::-webkit-scrollbar-thumb {
	background-color: #ddd;
	outline: 0px solid slategrey;
} */
body {
	font-family: 'NanumSquare', 'Malgun Gothic', '맑은 고딕', Helvetica, Arial,
		sans-serif !important;
	font-style: normal;
	font-weight: 400;
	font-size: 13px;
	margin: 0 auto;
	color: #000;
	padding-top: 43px !important;
	padding-bottom: 45px !important;
	background-color: #fff;
	overflow-x: hidden;
	max-width: 640px;
}

body {
	height: 800px
}

.img-responsive {
	margin: 0 auto;
}

img {
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
	padding: 10px 15px;
	margin-right: 10px;
	background-color: #95d9f9;
	border: none;
	border-radius: 5px;
}

button.btn:hover {
	background-color: white;
	border: 1px solid #95d9f9;
}

.list_group {
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

		<button class="list_group_item btn" data-mapx="60" data-mapy="74" type="button">화정동</button>
		<button class="list_group_item btn" data-mapx="60" data-mapy="74" type="button">동명동</button>
		<!-- <button class="list_group_item btn" data-mapx="60" data-mapy="74" data-dust="광주">동명동</button> -->
		<button class="list_group_item btn" data-mapx="60" data-mapy="74" type="button">봉선동</button>
		<button class="list_group_item btn" data-mapx="60" data-mapy="74" type="button">각화동</button>
		<button class="list_group_item btn" data-mapx="60" data-mapy="74" type="button">풍암동</button>
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

	<script>
   const div_menu = document.querySelector("div.list_group");

   div_menu?.addEventListener("click", (e) => {
     const button = e.target;
     
     if (button.tagName === "BUTTON") {
       const mapx = button.dataset.mapx;
       const mapy = button.dataset.mapy;
       
       console.log(mapx, mapy);
       console.log(`${rootPath}/detail/${mapx},${mapy}/list`);
       /* document.location.href = `${rootPath}/detail/${mapx},${mapy}/list`; */
     }
   });
   </script>

</body>
</html>