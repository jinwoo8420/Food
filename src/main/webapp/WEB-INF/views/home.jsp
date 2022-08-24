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

<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">

<style>
html::-webkit-scrollbar, ul::-webkit-scrollbar, div::-webkit-scrollbar {
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
}

.splash {
	width: 100%;
	height: 100vh;
}

.splash img {
	position: absolute;
	top: 40%;
	left: 50%;
	transform: translate(-50%, -50%);
}

p {
	font-family: 'Nanum Gothic Coding', monospace;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	font-size: 30px;
	margin-top: 50px;
	font-weight: bold;
}

.img-responsive {
	margin: 0 auto;
}

.d-flex {
	display: -ms-flexbox !important;
	display: flex !important;
}

.align-items-center {
	-ms-flex-align: center !important;
	align-items: center !important;
}

img {
	width: 250px;
}
</style>
</head>
<body>
	<div class="splash">
		<div class="d-flex align-items-center">
			<a href="${rootPath}/intro">
				<img src="${rootPath}/static/img/main.png" class="img-responsive">
			</a>
			<p>오늘은 뭐먹지?</p>
		</div>
	</div>
</body>
</html>